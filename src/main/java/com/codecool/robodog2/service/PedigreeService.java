package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.dao.PedigreeJdbcDao;
import com.codecool.robodog2.dto.PedigreeDto;
import com.codecool.robodog2.dto.PedigreeForADogDto;
import com.codecool.robodog2.dto.PuppyNameAndParentsDto;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedigreeService {

    private PedigreeJdbcDao pedigreeJdbcDao;
    private DogDAO dogDAO;

    @Autowired
    public PedigreeService(PedigreeJdbcDao pedigreeJdbcDao, @Qualifier("dogJdbcDao") DogDAO dogDAO) {
        this.pedigreeJdbcDao = pedigreeJdbcDao;
        this.dogDAO = dogDAO;
    }

    public void addPedigree(PedigreeDto pedigreeDto) {
        pedigreeJdbcDao.addPedigree(new Pedigree(pedigreeDto));
    }

    public List<Pedigree> listPedigrees() {
        return pedigreeJdbcDao.listPedigrees();
    }

    public Pedigree getPedigree(long id) {
        return pedigreeJdbcDao.getPedigree(id);
    }

    public void updatePedigree(PedigreeDto pedigreeDto, long id) {
        pedigreeJdbcDao.updatePedigree(new Pedigree(pedigreeDto), id);
    }

    public void deletePedigree(long id) {
        pedigreeJdbcDao.deletePedigree(id);
    }


    public Set<Dog> getCloseFamily(long dogId) {
        Set<Dog> closeFamily = new HashSet<>();

        Dog dad = getDad(dogId);
        if (dad != null) closeFamily.add(dad);

        Dog mom = getMom(dogId);
        if (mom != null) closeFamily.add(mom);

        closeFamily.addAll(getChildren(dogId));

        return closeFamily;
    }

    public Set<Dog> getFamily(long dogId) {
        Set<Dog> family = new HashSet<>();
        //TODO recursion
        Set<Dog> closeFamilySet = getCloseFamily(dogId);
        for (Dog dog : closeFamilySet) {
            Set<Dog> thisDogFamily = getCloseFamily(dog.getId());
            family.addAll(thisDogFamily);
        }
        return family;
    }

    public void addPedigreeForADog(long puppyId, PedigreeForADogDto pedigreeForADogDto) {
        Pedigree pedigreeForTheDog = new Pedigree(pedigreeForADogDto);
        pedigreeForTheDog.setPuppyId(puppyId);
        pedigreeJdbcDao.addPedigreeForADog(pedigreeForTheDog);
    }

    public void addPuppy(PuppyNameAndParentsDto puppyNameAndParentsDto) {
        Breed breed = puppyBreed(puppyNameAndParentsDto.getMomId(), puppyNameAndParentsDto.getDadId());
        Dog littleDog = new Dog(breed, puppyNameAndParentsDto.getName(), 0);
        long littleDogId = dogDAO.addDogAndReturnId(littleDog);

        Pedigree pedigree = new Pedigree(littleDogId, puppyNameAndParentsDto.getMomId(), puppyNameAndParentsDto.getDadId());
        pedigreeJdbcDao.createPuppyPedigree(pedigree);
    }

    private Breed puppyBreed(long momId, long dadId) {
        Random r = new Random();
        int randomBreedNumber = r.nextInt(2);
        if (randomBreedNumber == 0) {
            return dogDAO.getDog(momId).getBreed();
        } else {
            return dogDAO.getDog(dadId).getBreed();
        }
    }

    public Dog getDad(long puppyId) {
        if (pedigreeJdbcDao.getDad(puppyId).isPresent()) {
            return dogDAO.getDog(pedigreeJdbcDao.getDad(puppyId).get());
        }
        return null;
    }

    public Dog getMom(long puppyId) {
        if (pedigreeJdbcDao.getMom(puppyId).isPresent()) {
            return dogDAO.getDog(pedigreeJdbcDao.getMom(puppyId).get());
        }
        return null;
    }

    public Set<Dog> getChildren(long dogId) {
        if (pedigreeJdbcDao.getChildren(dogId).isPresent()) {
            Set<Long> childrenIds = new HashSet<>(pedigreeJdbcDao.getChildren(dogId).get());
            Set<Dog> children = new HashSet<>();
            for (Long childrenId : childrenIds) {
                children.add(dogDAO.getDog(childrenId));
            }
            return children;
        }
        return null;
    }

    public Set<Dog> getSiblings(long id) {
        List<Dog> dogList = dogDAO.listDogs();
        Set<Dog> siblings = new HashSet<>();
        for (Dog dog : dogList) {
            if (dog.getId() == id) {
                continue;
            }
            boolean sameMom = false;
            boolean sameDad = false;
            Optional<Long> dogMom = pedigreeJdbcDao.getMom(dog.getId());
            Optional<Long> actDogMom = pedigreeJdbcDao.getMom(id);
            if (dogMom.isPresent() && actDogMom.isPresent()) {
                Long mom1 = dogMom.get();
                Long mom2 = actDogMom.get();
                sameMom = mom1.equals(mom2);
            }

            if (pedigreeJdbcDao.getDad(dog.getId()).isPresent() && pedigreeJdbcDao.getDad(id).isPresent()) {
                sameDad = pedigreeJdbcDao.getDad(dog.getId()).get().equals(pedigreeJdbcDao.getDad(id).get());
            }

            if (sameMom || sameDad) {
                siblings.add(dog);
            }
        }
        return siblings;
    }
}
