package in.nucleusteq.plasma.service.impl;

import in.nucleusteq.plasma.dao.FinancialDetailRepository;
import in.nucleusteq.plasma.dao.RoleRepository;
import in.nucleusteq.plasma.dao.SkillRepository;
import in.nucleusteq.plasma.dao.UserAddressRepository;
import in.nucleusteq.plasma.dao.UserPersonalDetailRepository;
import in.nucleusteq.plasma.dao.UserSkillRepository;
import in.nucleusteq.plasma.dao.DesignationRepository;
import in.nucleusteq.plasma.dao.DocumentRepository;
import in.nucleusteq.plasma.dao.EmployeeRepository;
import in.nucleusteq.plasma.dao.UserWorkDetailRepository;
import in.nucleusteq.plasma.dto.common.SkillDTO;
import in.nucleusteq.plasma.dto.in.user.RecentOnBoardingInDTO;
import in.nucleusteq.plasma.dto.in.user.RecentOnBoardingWithDocumentInDto;
import in.nucleusteq.plasma.dto.in.user.RejectOnBoardInDTO;
import in.nucleusteq.plasma.dto.in.user.UpdatedDetailOfOnBoardInDTO;
import in.nucleusteq.plasma.dto.out.user.DetailOfRecentOnBoardingOutDTO;
import in.nucleusteq.plasma.dto.out.user.PendingOnBoardOutDTO;
import in.nucleusteq.plasma.dto.out.user.RecentOnBoardingListOutDTO;
import in.nucleusteq.plasma.enums.OnBoardingStatus;
import in.nucleusteq.plasma.enums.OnshoreOrOffshore;
//import in.nucleusteq.plasma.enums.Role;
import in.nucleusteq.plasma.exception.NotFoundException;
import in.nucleusteq.plasma.entity.Designation;
import in.nucleusteq.plasma.entity.Employee;
import in.nucleusteq.plasma.entity.FinancialDetail;
import in.nucleusteq.plasma.entity.Role;
import in.nucleusteq.plasma.entity.Skills;
import in.nucleusteq.plasma.entity.UserAddress;
import in.nucleusteq.plasma.entity.UserPersonalDetail;
import in.nucleusteq.plasma.entity.UserSkill;
import in.nucleusteq.plasma.entity.UserWorkDetail;
import in.nucleusteq.plasma.payload.SuccessResponse;
import in.nucleusteq.plasma.payload.UserIdEmailGeneration;
import in.nucleusteq.plasma.service.EmployeeService;
import in.nucleusteq.plasma.utility.FileStorageProperties;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for managing employee-related operations.
 */
@Service
public class EmployeeServiceImplementation implements EmployeeService {
    /**
     * User Personal Detail Repository.
     */
    @Autowired
    private UserPersonalDetailRepository userPersonalDetailRepository;
    /**
     * User Work Detail Repository.
     */
    @Autowired
    private UserWorkDetailRepository userWorkDetailRepository;
    /**
     * User Address Repository.
     */
    @Autowired
    private UserAddressRepository userAddressRepository;
    /**
     * Employee Repository.
     */
    @Autowired
    private EmployeeRepository userRepository;
    /**
     * Financial Detail Repository.
     */
    @Autowired
    private FinancialDetailRepository financialDetailRepository;
    /**
     * Skill Repository.
     */
    @Autowired
    private SkillRepository skillRepository;
    /**
     * User Skill Repository.
     */
    @Autowired
    private UserSkillRepository userSkillRepository;
    /**
     * Designation Repository.
     */
    @Autowired
    private DesignationRepository designationRepository;
    /**
     * Document Repository.
     */
    @Autowired
    private DocumentRepository documentRepository;
    /**
     * File Storage Location.
     */
    private Path fileStorageLocation;
    /**
     * File Storage Properties.
     */
    @Autowired
    private FileStorageProperties fileStorageProperties;
    /**
     * Role Repository.
     */
    @Autowired
    private RoleRepository roleRepository;
    /**
     * Constructs an EmployeeServiceImplementation instance. Initializes file
     * storage location.
     * @param fileStorageProperties The file storage properties.
     */
//    public EmployeeServiceImplementation(FileStorageProperties fileStorageProperties) {
//        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.");
//        }
//    }
    /**
     * Processes recent onboarding of an employee.
     * @param recentOnboardInDTO The DTO containing recent onboarding details.
     * @return A success response indicating the status of the operation.
     */
    @Override
    @Transactional
    public SuccessResponse processRecentOnboard(RecentOnBoardingInDTO recentOnboardInDTO) {
        UserAddress userAddress = new UserAddress(recentOnboardInDTO.getAddressLine1(),
                recentOnboardInDTO.getAddressLine2(), recentOnboardInDTO.getCity(), recentOnboardInDTO.getState(),
                recentOnboardInDTO.getCountry(), recentOnboardInDTO.getZipCode());
        userAddressRepository.save(userAddress);
        Designation designation = designationRepository.findByDesignationName(recentOnboardInDTO.getDesignation())
                .orElseThrow(() -> new NotFoundException(
                        "Designation not found for name: " + recentOnboardInDTO.getDesignation()));
        UserWorkDetail userWorkDetail = new UserWorkDetail();
        userWorkDetail.setCitizenship(recentOnboardInDTO.getCitizenship());
        userWorkDetail.setContractingCompany(recentOnboardInDTO.getContractingCompany());
        userWorkDetail.setContractingRate(recentOnboardInDTO.getContractingRate());
        userWorkDetail.setContractingRateCurrency(recentOnboardInDTO.getContractingRateCurrency());
        userWorkDetail.setDesignation(designation);
        userWorkDetail.setEmploymentCompany(recentOnboardInDTO.getEmploymentCompany());
        userWorkDetail.setEmploymentNature(recentOnboardInDTO.getEmploymentNature());
        userWorkDetail.setEmploymentStartDate(recentOnboardInDTO.getEmploymentStartDate());
        userWorkDetail.setEmploymentStatus(recentOnboardInDTO.getEmployementStatus());
        userWorkDetail.setOnshoreOrOffshore(recentOnboardInDTO.getOnshoreOrOffshore());
        // userWorkDetail.setRole(recentOnboardInDTO.getRole());
        userWorkDetail.setVisaStatus(recentOnboardInDTO.getVisaStatus());
        userWorkDetail.setWorkLocation(recentOnboardInDTO.getWorkLocation());
        userWorkDetail.setWorkMode(recentOnboardInDTO.getWorkMode());
        Employee recruiter = userRepository.findById(recentOnboardInDTO.getRecruiter_id()).orElseThrow(
                () -> new NotFoundException("Recruiter not found for ID: " + recentOnboardInDTO.getRecruiter_id()));
        userWorkDetail.setRecruiter(recruiter);
        userWorkDetailRepository.save(userWorkDetail);
        List<Skills> skills = new ArrayList<>();
        for (String skillName : recentOnboardInDTO.getSkill()) {
            Skills skill = skillRepository.findBySkillName(skillName).orElseGet(() -> {
                Skills newSkill = new Skills(skillName);
                return skillRepository.save(newSkill);
            });
            skills.add(skill);
        }
        for (Skills skill : skills) {
            UserSkill employeeSkill = new UserSkill(userWorkDetail, skill);
            userSkillRepository.save(employeeSkill);
        }
        UserPersonalDetail userPersonalDetail = new UserPersonalDetail(recentOnboardInDTO.getFirstName(),
                recentOnboardInDTO.getMiddleName(), recentOnboardInDTO.getLastName(),
                recentOnboardInDTO.getPhoneNumber(), recentOnboardInDTO.getPersonalEmailId(),
                recentOnboardInDTO.getDateOfBirth(), recentOnboardInDTO.getGender(), recentOnboardInDTO.getBloodGroup(),
                OnBoardingStatus.PENDING, userAddress, userWorkDetail);
        userPersonalDetailRepository.save(userPersonalDetail);
        return new SuccessResponse("new onborading successfuly", 200);
    }
    /**
     * Retrieves a list of pending onboarding users.
     * @return A list of pending onboarding DTOs.
     */
    @Override
    public List<PendingOnBoardOutDTO> getPendingOnBoardUsers() {
        List<UserPersonalDetail> pendingUsers = userPersonalDetailRepository
                .findByOnBoardingStatus(OnBoardingStatus.PENDING);
        return pendingUsers.stream().map(this::mapToPendingOnBoardOutDTO).toList();
    }
    /**
     * Retrieves details of a recent onboarding form.
     * @param id The ID of the user.
     * @return Details of the recent onboarding form.
     */
    @Override
    public DetailOfRecentOnBoardingOutDTO onboardForm(int id) {
        UserPersonalDetail userPersonalDetail = userPersonalDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found for ID: " + id));
        DetailOfRecentOnBoardingOutDTO onboardInDTO = new DetailOfRecentOnBoardingOutDTO();
        UserAddress userAddress = userPersonalDetail.getUserAddress();
        UserWorkDetail userWorkDetail = userPersonalDetail.getUserWorkDetail();
        onboardInDTO.setAddressLine1(userAddress.getAddress1());
        onboardInDTO.setAddressLine2(userAddress.getAddress2());
        onboardInDTO.setBloodGroup(userPersonalDetail.getBloodGroup());
        onboardInDTO.setCitizenship(userWorkDetail.getCitizenship());
        onboardInDTO.setCity(userAddress.getCity());
        onboardInDTO.setContractingCompany(userWorkDetail.getContractingCompany());
        onboardInDTO.setContractingRate(userWorkDetail.getContractingRate());
        onboardInDTO.setContractingRateCurrency(userWorkDetail.getContractingRateCurrency());
        onboardInDTO.setCountry(userAddress.getCountry());
        onboardInDTO.setDateOfBirth(userPersonalDetail.getDateOfBirth());
        onboardInDTO.setDesignation(userWorkDetail.getDesignation().getDesignationName());
        onboardInDTO.setEmployementStatus(userWorkDetail.getEmploymentStatus());
        onboardInDTO.setEmploymentCompany(userWorkDetail.getEmploymentCompany());
        onboardInDTO.setEmploymentNature(userWorkDetail.getEmploymentNature());
        onboardInDTO.setEmploymentStartDate(userWorkDetail.getEmploymentStartDate());
        onboardInDTO.setFirstName(userPersonalDetail.getFirstName());
        onboardInDTO.setGender(userPersonalDetail.getGender());
        onboardInDTO.setId(userPersonalDetail.getId());
        onboardInDTO.setLastName(userPersonalDetail.getLastName());
        onboardInDTO.setMiddleName(userPersonalDetail.getMiddleName());
        onboardInDTO.setOnshoreOrOffshore(userWorkDetail.getOnshoreOrOffshore());
        onboardInDTO.setPersonalEmailId(userPersonalDetail.getPersonalEmailId());
        onboardInDTO.setPhoneNumber(userPersonalDetail.getPhoneNumber());
        // onboardInDTO.setRole(userWorkDetail.getRole());
        onboardInDTO.setState(userAddress.getState());
        onboardInDTO.setVisaStatus(userWorkDetail.getVisaStatus());
        onboardInDTO.setWorkLocation(userWorkDetail.getWorkLocation());
        onboardInDTO.setWorkMode(userWorkDetail.getWorkMode());
        onboardInDTO.setZipCode(userAddress.getZipCode());
        List<SkillDTO> skillDTOs = userSkillRepository.findByUserWorkDetail(userWorkDetail).stream()
                .map(userSkill -> new SkillDTO(userSkill.getSkill().getSkillName())).collect(Collectors.toList());
        onboardInDTO.setSkills(skillDTOs);
        return onboardInDTO;
    }
    /**
     * Updates details of an onboarded employee.
     * @param uBoardInDTO The DTO containing updated details.
     * @return A success response indicating the status of the operation.
     */
    @Override
    @Transactional
    public SuccessResponse updateDetailOfOnBoard(UpdatedDetailOfOnBoardInDTO uBoardInDTO) {
        UserPersonalDetail userPersonalDetail = userPersonalDetailRepository.findById(uBoardInDTO.getId())
                .orElseThrow(() -> new NotFoundException("User not found for ID: " + uBoardInDTO.getId()));
        String newUserId = UserIdEmailGeneration.generateUserId(userRepository.findLastUserId());
        String newEmail = UserIdEmailGeneration.generateEmail(userPersonalDetail.getFirstName(),
                userPersonalDetail.getLastName());
        String newPassword = UserIdEmailGeneration.generatePassword();
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setCtc(uBoardInDTO.getCtc());
        financialDetail.setBonus(uBoardInDTO.getBonus());
        financialDetail.setVisaExpense(uBoardInDTO.getVisaExpense());
        financialDetail.setOtherExpense(uBoardInDTO.getOtherExpense());
        financialDetail.setCtcCurrency(uBoardInDTO.getCtcCurrency());
        financialDetail.setBonusCurrency(uBoardInDTO.getBonusCurrency());
        financialDetail.setVisaExpenseCurrency(uBoardInDTO.getVisaExpenseCurrency());
        financialDetail.setOtherExpenseCurrency(uBoardInDTO.getOtherExpenseCurrency());
        financialDetailRepository.save(financialDetail);
        Employee user = new Employee();
        user.setUserId(newUserId);
        user.setEmail(newEmail);
        user.setPassword(newPassword);
        user.setFinancialDetail(financialDetail);
        user.setUserPersonalDetail(userPersonalDetail);
        user.setUserWorkDetail(userPersonalDetail.getUserWorkDetail());
        userRepository.save(user);
        Employee recruiterManager = userRepository.findById(uBoardInDTO.getRecruiterManager()).orElseThrow(
                () -> new NotFoundException("Recruiter not found for ID: " + uBoardInDTO.getRecruiterManager()));
        userPersonalDetail.getUserWorkDetail().setRecruiterManager(recruiterManager);
        userPersonalDetail.setOnBoardingStatus(OnBoardingStatus.APPROVED);
        userPersonalDetailRepository.save(userPersonalDetail);
        return new SuccessResponse("New OnBoarding with finance and all other detail updated successfully", 200);
    }
    /**
     * Rejects an onboarding request.
     * @param rejectOnBoard The DTO containing rejection details.
     * @return A success response indicating the status of the operation.
     */
    @Override
    public SuccessResponse rejectOnboard(RejectOnBoardInDTO rejectOnBoard) {
        UserPersonalDetail userPersonalDetail = userPersonalDetailRepository.findById(rejectOnBoard.getId())
                .orElseThrow(() -> new NotFoundException("User not found for ID: " + rejectOnBoard.getId()));
        userPersonalDetail.setOnBoardingStatus(rejectOnBoard.getOnBoardingStatus());
        userPersonalDetailRepository.save(userPersonalDetail);
        return new SuccessResponse("On-board status rejected successfully", 200);
    }
    /**
     * Retrieves a list of recent onboarding users.
     * @return A list of recent onboarding DTOs.
     */
    @Override
    public List<RecentOnBoardingListOutDTO> getListOfRecentOnboarding() {
        LocalDate currentDate = LocalDate.now();
        YearMonth currentYearMonth = YearMonth.from(currentDate);
        YearMonth previousYearMonth = currentYearMonth.minusMonths(1);
        LocalDate firstDayOfPreviousMonth = LocalDate.of(previousYearMonth.getYear(), previousYearMonth.getMonth(), 1);
        List<Integer> recentUserWorkIds = userWorkDetailRepository.findByEmploymentStartDateBetween(
                java.sql.Date.valueOf(firstDayOfPreviousMonth), java.sql.Date.valueOf(currentDate));
        List<Employee> recentEmployees = userRepository.findByUserWorkDetailIdIn(recentUserWorkIds);
        List<RecentOnBoardingListOutDTO> recentOnBoardingList = recentEmployees.stream()
                .map(this::mapToRecentOnBoardingListDTO).collect(Collectors.toList());
        return recentOnBoardingList;
    }
    /**
     * Retrieves a list of all employees.
     * @return A list of all employee onboarding DTOs.
     */
    @Override
    public List<RecentOnBoardingListOutDTO> getAllEmployees() {
        List<Employee> allEmployees = userRepository.findAll();
        List<RecentOnBoardingListOutDTO> allEmployeeBoardingListOutDTOs = new ArrayList<>();
        for (Employee employee : allEmployees) {
            UserPersonalDetail userPersonalDetail = employee.getUserPersonalDetail();
            UserAddress userAddress = userPersonalDetail.getUserAddress();
            RecentOnBoardingListOutDTO dto = new RecentOnBoardingListOutDTO();
            dto.setEmployeeId(employee.getUserId());
            dto.setFirstName(userPersonalDetail.getFirstName());
            dto.setLastName(userPersonalDetail.getLastName());
            dto.setStartDate(userPersonalDetail.getUserWorkDetail().getEmploymentStartDate());
            dto.setPhoneNumber(userPersonalDetail.getPhoneNumber());
            dto.setCity(userAddress.getCity());
            dto.setState(userAddress.getState());
            allEmployeeBoardingListOutDTOs.add(dto);
        }
        return allEmployeeBoardingListOutDTOs;
    }
    /**
     * Maps a UserPersonalDetail object to a PendingOnBoardOutDTO object.
     * @param user The UserPersonalDetail object to map.
     * @return PendingOnBoardOutDTO object mapped from the UserPersonalDetail.
     */
    private PendingOnBoardOutDTO mapToPendingOnBoardOutDTO(UserPersonalDetail user) {
        PendingOnBoardOutDTO dto = new PendingOnBoardOutDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setStartDate(user.getUserWorkDetail().getEmploymentStartDate().toString());
        dto.setCity(user.getUserAddress().getCity());
        dto.setState(user.getUserAddress().getState());
        return dto;
    }
    /**
     * Maps an Employee object to a RecentOnBoardingListOutDTO object.
     * @param employee The Employee object to map.
     * @return RecentOnBoardingListOutDTO object mapped from the Employee.
     */
    private RecentOnBoardingListOutDTO mapToRecentOnBoardingListDTO(Employee employee) {
        UserPersonalDetail userPersonalDetail = employee.getUserPersonalDetail();
        UserAddress userAddress = userPersonalDetail.getUserAddress();
        RecentOnBoardingListOutDTO dto = new RecentOnBoardingListOutDTO();
        dto.setEmployeeId(employee.getUserId());
        dto.setFirstName(userPersonalDetail.getFirstName());
        dto.setLastName(userPersonalDetail.getLastName());
        dto.setStartDate(userPersonalDetail.getUserWorkDetail().getEmploymentStartDate());
        dto.setPhoneNumber(userPersonalDetail.getPhoneNumber());
        dto.setCity(userAddress.getCity());
        dto.setState(userAddress.getState());
        return dto;
    }
    /**
     * Processes recent onboarding with documents.
     * @param recentOnboardInDTO The RecentOnBoardingWithDocumentInDto containing
     *                           recent onboarding details.
     */
    @Override
    @Transactional
    public void processRecentOnboardWithDocument(RecentOnBoardingWithDocumentInDto recentOnboardInDTO) {
        UserAddress userAddress = new UserAddress(recentOnboardInDTO.getAddressLine1(),
                recentOnboardInDTO.getAddressLine2(), recentOnboardInDTO.getCity(), recentOnboardInDTO.getState(),
                recentOnboardInDTO.getCountry(), recentOnboardInDTO.getZipCode());
        userAddressRepository.save(userAddress);
        Designation designation = designationRepository.findByDesignationName(recentOnboardInDTO.getDesignation())
                .orElseThrow(() -> new NotFoundException(
                        "Designation not found for name: " + recentOnboardInDTO.getDesignation()));
        UserWorkDetail userWorkDetail = new UserWorkDetail();
        userWorkDetail.setCitizenship(recentOnboardInDTO.getCitizenship());
        userWorkDetail.setContractingCompany(recentOnboardInDTO.getContractingCompany());
        userWorkDetail.setContractingRate(recentOnboardInDTO.getContractingRate());
        userWorkDetail.setContractingRateCurrency(recentOnboardInDTO.getContractingRateCurrency());
        userWorkDetail.setDesignation(designation);
        userWorkDetail.setEmploymentCompany(recentOnboardInDTO.getEmploymentCompany());
        userWorkDetail.setEmploymentNature(recentOnboardInDTO.getEmploymentNature());
        userWorkDetail.setEmploymentStartDate(recentOnboardInDTO.getEmploymentStartDate());
        userWorkDetail.setEmploymentStatus(recentOnboardInDTO.getEmployementStatus());
        userWorkDetail.setOnshoreOrOffshore(recentOnboardInDTO.getOnshoreOrOffshore());
        // userWorkDetail.setRole(recentOnboardInDTO.getRole());
        userWorkDetail.setVisaStatus(recentOnboardInDTO.getVisaStatus());
        userWorkDetail.setWorkLocation(recentOnboardInDTO.getWorkLocation());
        userWorkDetail.setWorkMode(recentOnboardInDTO.getWorkMode());
        Employee recruiter = userRepository.findById(recentOnboardInDTO.getRecruiter_id()).orElseThrow(
                () -> new NotFoundException("Recruiter not found for ID: " + recentOnboardInDTO.getRecruiter_id()));
        userWorkDetail.setRecruiter(recruiter);
        userWorkDetailRepository.save(userWorkDetail);
        List<Skills> skills = new ArrayList<>();
        for (String skillName : recentOnboardInDTO.getSkill()) {
            Skills skill = skillRepository.findBySkillName(skillName).orElseGet(() -> {
                Skills newSkill = new Skills(skillName);
                return skillRepository.save(newSkill);
            });
            skills.add(skill);
        }
        for (Skills skill : skills) {
            UserSkill employeeSkill = new UserSkill(userWorkDetail, skill);
            userSkillRepository.save(employeeSkill);
        }
        UserPersonalDetail userPersonalDetail = new UserPersonalDetail(recentOnboardInDTO.getFirstName(),
                recentOnboardInDTO.getMiddleName(), recentOnboardInDTO.getLastName(),
                recentOnboardInDTO.getPhoneNumber(), recentOnboardInDTO.getPersonalEmailId(),
                recentOnboardInDTO.getDateOfBirth(), recentOnboardInDTO.getGender(), recentOnboardInDTO.getBloodGroup(),
                OnBoardingStatus.PENDING, userAddress, userWorkDetail);
        userPersonalDetailRepository.save(userPersonalDetail);
        saveDocuments(userPersonalDetail, recentOnboardInDTO);
    }
    /**
     * Saves document details for a user.
     * @param userPersonalDetail The UserPersonalDetail object for the user.
     * @param recentOnboardInDTO The RecentOnBoardingWithDocumentInDto containing
     *                           document details.
     */
    private void saveDocuments(UserPersonalDetail userPersonalDetail,
            RecentOnBoardingWithDocumentInDto recentOnboardInDTO) {
        if (recentOnboardInDTO.getOnshoreOrOffshore() == OnshoreOrOffshore.OFFSHORE) {
            saveOffshoreDocumentDetails(userPersonalDetail, recentOnboardInDTO);
        } else if (recentOnboardInDTO.getOnshoreOrOffshore() == OnshoreOrOffshore.ONSHORE) {
            saveOnshoreDocumentDetails(userPersonalDetail, recentOnboardInDTO);
        }
    }
    /**
     * Saves offshore document details for a user.
     * @param userPersonalDetail The UserPersonalDetail object for the user.
     * @param recentOnboardInDTO The RecentOnBoardingWithDocumentInDto containing
     *                           document details.
     */
    private void saveOffshoreDocumentDetails(UserPersonalDetail userPersonalDetail,
            RecentOnBoardingWithDocumentInDto recentOnboardInDTO) {
    }
    /**
     * Saves onshore document details for a user.
     * @param userPersonalDetail The UserPersonalDetail object for the user.
     * @param recentOnboardInDTO The RecentOnBoardingWithDocumentInDto containing
     *                           document details.
     */
    private void saveOnshoreDocumentDetails(UserPersonalDetail userPersonalDetail,
            RecentOnBoardingWithDocumentInDto recentOnboardInDTO) {
    }
    /**
     * Retrieves detailed information of a recent onboarding based on the user ID.
     * @param id The ID of the user.
     * @return DetailOfRecentOnBoardingOutDTO containing detailed information of the
     *         recent onboarding.
     */
    public DetailOfRecentOnBoardingOutDTO onboardForm1(int id) {
        UserPersonalDetail userPersonalDetail = userPersonalDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found for ID: " + id));
        DetailOfRecentOnBoardingOutDTO onboardInDTO = new DetailOfRecentOnBoardingOutDTO();
        UserAddress userAddress = userPersonalDetail.getUserAddress();
        UserWorkDetail userWorkDetail = userPersonalDetail.getUserWorkDetail();
        onboardInDTO.setAddressLine1(userAddress.getAddress1());
        onboardInDTO.setAddressLine2(userAddress.getAddress2());
        onboardInDTO.setBloodGroup(userPersonalDetail.getBloodGroup());
        onboardInDTO.setCitizenship(userWorkDetail.getCitizenship());
        onboardInDTO.setCity(userAddress.getCity());
        onboardInDTO.setContractingCompany(userWorkDetail.getContractingCompany());
        onboardInDTO.setContractingRate(userWorkDetail.getContractingRate());
        onboardInDTO.setContractingRateCurrency(userWorkDetail.getContractingRateCurrency());
        onboardInDTO.setCountry(userAddress.getCountry());
        onboardInDTO.setDateOfBirth(userPersonalDetail.getDateOfBirth());
        onboardInDTO.setDesignation(userWorkDetail.getDesignation().getDesignationName());
        onboardInDTO.setEmployementStatus(userWorkDetail.getEmploymentStatus());
        onboardInDTO.setEmploymentCompany(userWorkDetail.getEmploymentCompany());
        onboardInDTO.setEmploymentNature(userWorkDetail.getEmploymentNature());
        onboardInDTO.setEmploymentStartDate(userWorkDetail.getEmploymentStartDate());
        onboardInDTO.setFirstName(userPersonalDetail.getFirstName());
        onboardInDTO.setGender(userPersonalDetail.getGender());
        onboardInDTO.setId(userPersonalDetail.getId());
        onboardInDTO.setLastName(userPersonalDetail.getLastName());
        onboardInDTO.setMiddleName(userPersonalDetail.getMiddleName());
        onboardInDTO.setOnshoreOrOffshore(userWorkDetail.getOnshoreOrOffshore());
        onboardInDTO.setPersonalEmailId(userPersonalDetail.getPersonalEmailId());
        onboardInDTO.setPhoneNumber(userPersonalDetail.getPhoneNumber());
// onboardInDTO.setRole(userWorkDetail.getRole());
        Set<Role> validatedRoles = validateAndRetrieveRoles(userWorkDetail.getRoles());
//onboardInDTO.setRoles(validatedRoles);
        onboardInDTO.setState(userAddress.getState());
        onboardInDTO.setVisaStatus(userWorkDetail.getVisaStatus());
        onboardInDTO.setWorkLocation(userWorkDetail.getWorkLocation());
        onboardInDTO.setWorkMode(userWorkDetail.getWorkMode());
        onboardInDTO.setZipCode(userAddress.getZipCode());
        List<SkillDTO> skillDTOs = userSkillRepository.findByUserWorkDetail(userWorkDetail).stream()
                .map(userSkill -> new SkillDTO(userSkill.getSkill().getSkillName())).collect(Collectors.toList());
        onboardInDTO.setSkills(skillDTOs);
        return onboardInDTO;
    }
    /**
     * Validate And Retrieve Role.
     * @param roles
     * @return validateRoles
     */
    private Set<Role> validateAndRetrieveRoles(Set<Role> roles) {
        Set<Role> validatedRoles = new HashSet<>();
        for (Role role : roles) {
            try {
                Role existingRole = roleRepository.findById(role.getRole_id())
                        .orElseThrow(() -> new RuntimeException("Role does not exist with id: " + role.getRole_id()));
                if (!existingRole.getName().equals(role.getName())) {
                    throw new RuntimeException("Role name mismatch for id: " + role.getRole_id());
                }
                validatedRoles.add(existingRole);
            } catch (Exception e) {
                throw new RuntimeException("Error validating role existence for id: " + role.getRole_id(), e);
            }
        }
        return validatedRoles;
    }
}
