package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.WorkerToManagerDTO;
import com.pentalog.KitKat.DTO.WorkerToManagerDashboardDTO;
import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.Skill;
import com.pentalog.KitKat.Entities.SkillRating;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class WorkersToManagerDashboardService {

    private final UserRepository userRepository;
    private final SeniorityRepository seniorityRepository;
    private final PositionRepository positionRepository;
    private final LanguageRepository languageRepository;
    private final SkillRatingRepository skillRatingRepository;
    private final SkillRepository skillRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final ProjectRepository projectRepository;

    public WorkersToManagerDashboardService(UserRepository userRepository, SeniorityRepository seniorityRepository, PositionRepository positionRepository, LanguageRepository languageRepository, SkillRatingRepository skillRatingRepository, SkillRepository skillRepository, RoleRepository roleRepository, CityRepository cityRepository, CountryRepository countryRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.seniorityRepository = seniorityRepository;
        this.positionRepository = positionRepository;
        this.languageRepository = languageRepository;
        this.skillRatingRepository = skillRatingRepository;
        this.skillRepository = skillRepository;
        this.roleRepository = roleRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.projectRepository = projectRepository;
    }

    public List<WorkerToManagerDashboardDTO> returnWorkersToManagerDashboard() {
        List<WorkerToManagerDashboardDTO> workersToManagerDashboardList = new ArrayList<>();

        log.info("Extracting users from database");
        List<User> users = userRepository.findAll();
        for (User user : users) {
            //!!! When database will be populated, this: user.getRole() == null has to be replaced by:
            //Objects.equals(roleRepository.findById(user.getRole().getRoleId()).get().getName(), "Worker")
            if( user.getRole() == null) {
                WorkerToManagerDashboardDTO worker = new WorkerToManagerDashboardDTO();

                worker.setId(user.getUserId());

                if(user.getFirstName()==null) {
                    worker.setName(null);
                }
                else {
                    worker.setName(user.getFirstName());
                }

                if(user.getLastName() == null){
                    worker.setName(null);
                }
                else {
                    worker.setSurname(user.getLastName());
                }

                worker.setEmail(user.getEmail());

                if(user.getAvatar() == null){
                    worker.setAvatar(null);
                }
                else {
                    worker.setAvatar(user.getAvatar());
                }

                if(user.getSeniority() == null){
                    worker.setSeniority(null);
                }
                else {
                    worker.setSeniority(seniorityRepository.findById(user.getUserId()).get().getName());
                }

                if(user.getPosition() == null){
                    worker.setRole(null);
                }
                else {
                    worker.setRole(positionRepository.findById(user.getUserId()).get().getName());
                }

                if(user.getLanguages() == null){
                    worker.setLanguages(null);
                }
                else {
                    String languagesList = user.getLanguages();
                    String[] languagesIds = languagesList.split(",");
                    List<String> languages = new ArrayList<>();
                    for (String languageId : languagesIds) {
                        languages.add(languageRepository.findById(Integer.valueOf(languageId)).get().getLanguageName());
                    }
                    worker.setLanguages(languages);
                }

                if(user.getSkillRating() == null){
                    worker.setSkills(null);
                }
                else {
                    List<SkillRating> skillsRating = skillRatingRepository.findAllByUser_UserId(user.getUserId());
                    List<String> skills = new ArrayList<>();
                    for (SkillRating skillRating : skillsRating) {
                        skills.add(skillRepository.findById(skillRating.getSkill().getSkillId()).get().getName());
                    }
                    worker.setSkills(skills);
                }
                if(user.getProject()==null){
                    worker.setStatus("Available");
                }
                else{
                    worker.setStatus("On Project");
                }
                workersToManagerDashboardList.add(worker);
            }
        }

        return workersToManagerDashboardList;
    }

    public WorkerToManagerDTO returnWorkerById(Integer id){
        User user = userRepository.findById(id).get();
        WorkerToManagerDTO worker = new WorkerToManagerDTO();

        worker.setId(id);

        worker.setId(user.getUserId());

        if(user.getFirstName()==null) {
            worker.setName(null);
        }
        else {
            worker.setName(user.getFirstName());
        }

        if(user.getLastName() == null){
            worker.setName(null);
        }
        else {
            worker.setSurname(user.getLastName());
        }

        worker.setEmail(user.getEmail());

        if(user.getAvatar() == null){
            worker.setAvatar(null);
        }
        else {
            worker.setAvatar(user.getAvatar());
        }

        if(user.getSeniority() == null){
            worker.setSeniority(null);
        }
        else {
            worker.setSeniority(seniorityRepository.findById(user.getUserId()).get().getName());
        }

        if(user.getPosition() == null){
            worker.setRole(null);
        }
        else {
            worker.setRole(positionRepository.findById(user.getUserId()).get().getName());
        }

        if(user.getLanguages() == null){
            worker.setLanguages(null);
        }
        else {
            String languagesList = user.getLanguages();
            String[] languagesIds = languagesList.split(",");
            List<String> languages = new ArrayList<>();
            for (String languageId : languagesIds) {
                languages.add(languageRepository.findById(Integer.valueOf(languageId)).get().getLanguageName());
            }
            worker.setLanguages(languages);
        }

        if(user.getSkillRating() == null){
            worker.setSkills(null);
        }
        else {
            List<SkillRating> skillsRating = skillRatingRepository.findAllByUser_UserId(user.getUserId());
            List<String> skills = new ArrayList<>();
            for (SkillRating skillRating : skillsRating) {
                skills.add(skillRepository.findById(skillRating.getSkill().getSkillId()).get().getName());
            }
            worker.setSkills(skills);
        }

        if(user.getCity() == null){
            worker.setCity(null);
        }
        else{
            worker.setCity(cityRepository.findById(user.getCity().getCityId()).get().getCityName() + ", " +
                    countryRepository.findById(cityRepository.findById(user.getCity().getCityId()).get().getCountry().getCountryId()).get().getCountryName());
        }

        if(user.getCv() == null){
            worker.setCv(null);
        }
        else{
            worker.setCv(user.getCv());
        }

        if(user.getProject() == null){
            worker.setProject(null);
        }
        else{
            worker.setProject(projectRepository.findById(user.getProject().getProjectId()).get().getProjectName());
        }
        return worker;
    }
}
