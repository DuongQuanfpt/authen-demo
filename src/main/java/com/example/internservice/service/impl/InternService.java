package com.example.internservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.internservice.enumentity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.internservice.dao.InternRepository;
import com.example.internservice.dto.email.EmailDetails;
import com.example.internservice.dto.intern.InternDTO;
import com.example.internservice.dto.intern.InternDTOAdd;
import com.example.internservice.dto.intern.InternDTOUpdate;
import com.example.internservice.exception.custom.CustomNotFoundException;
import com.example.internservice.model.Intern;
import com.example.internservice.service.IInternService;

import net.bytebuddy.utility.RandomString;

@Service
public class InternService implements IInternService {
    @Autowired
    private InternRepository internRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public InternDTO save(InternDTOAdd dtoAdd) {
        Intern intern = new Intern(dtoAdd.getName(), dtoAdd.getAge(), dtoAdd.getPhone(), dtoAdd.getEmail(),
                passwordEncoder.encode(dtoAdd.getPassword()), Roles.fromInteger(dtoAdd.getRoleValue()));
        return toDTO(internRepository.save(intern));
    }

    @Override
    public List<InternDTO> getAll(int page, int size, String sortParam) {
        List<InternDTO> internAll = new ArrayList<>();
        Sort sort = Sort.by(Direction.ASC, sortParam);
        PageRequest paging = PageRequest.of(page, size, sort);

        for (Intern i : internRepository.findAll(paging)) {
            internAll.add(toDTO(i));
        }
        return internAll;
    }

    @Override
    public InternDTO getById(Long id) {
        Intern intern = internRepository.findById(id).orElse(null);
        return toDTO(intern);
    }

    public InternDTO update(Long id, InternDTOUpdate dtoUpdate) {
        Intern internOld = internRepository.findById(id).orElseThrow(() -> new CustomNotFoundException());

        if (dtoUpdate.getAge() != null) {
            internOld.setAge(dtoUpdate.getAge().intValue());
        }

        if (dtoUpdate.getName() != null) {
            internOld.setName(dtoUpdate.getName());
        }

        if (dtoUpdate.getEmail() != null) {
            internOld.setName(dtoUpdate.getName());
        }

        if (dtoUpdate.getPhone() != null) {
            internOld.setName(dtoUpdate.getName());
        }

        if (dtoUpdate.getPassword() != null) {
            internOld.setPassword(dtoUpdate.getPassword());
        }

        return toDTO(internRepository.save(internOld));
    }

    @Override
    public InternDTO delete(Long id) {
        Intern internOld = internRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("intern doesnt exist"));
        internRepository.delete(internOld);

        return toDTO(internOld);
    }

    @Override
    public String getActivateCode(Long id) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("intern doesnt exist"));

        // generate activation code
        intern.setActivationCode(RandomString.make(8));
        internRepository.save(intern);

        // remove the code after a period of time
        // using TimerTask
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                Intern i = internRepository.findById(id)
                        .orElseThrow(() -> new CustomNotFoundException("intern doesnt exist"));
                System.out.println(i.getName());
                if (i.getActivationCode() != null) {
                    i.setActivationCode(null);
                    internRepository.save(i);
                }
            }

        };
        timer.schedule(timerTask, 40000);

        EmailDetails details = new EmailDetails();
        details.setRecipient(intern.getEmail());
        details.setMsgBody("Here your verification code : " + intern.getActivationCode());
        details.setSubject("Verify your email");

        return emailService.sendSimpleMail(details);
    }

    @Override
    // @Scheduled(cron = "0/40 * * ? * * ")
    public void getCodeByRate() {
        List<Intern> inactiveInterns = internRepository.findByIsActiveFalse();
        List<Intern> result = new ArrayList<>();
        for (Intern intern : inactiveInterns) {
            intern.setActivationCode(RandomString.make(8));
            System.out.println(intern.getName() + "-- " + intern.isActive());
            result.add(intern);
        }
        internRepository.saveAll(result);

    }

    private InternDTO toDTO(Intern intern) {
        if (intern == null) {
            return null;
        }
        InternDTO response = new InternDTO(intern.getId(), intern.getName(), intern.getAge(),
                intern.getPhone(), intern.getEmail(), intern.getPassword(), intern.isActive(), intern.getRoles().toString());
        return response;
    }

    @Override
    public String active(String code) {
        Intern intern = internRepository.findByActivationCode(code)
                .orElseThrow(() -> new CustomNotFoundException("Invalid code"));
        intern.setActive(true);
        intern.setActivationCode(null);
        internRepository.save(intern);

        return "Intern " + intern.getEmail() + " have been activated ";
    }

}
