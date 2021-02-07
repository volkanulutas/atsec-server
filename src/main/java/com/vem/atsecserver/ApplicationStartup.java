package com.vem.atsecserver;

import com.vem.atsecserver.entity.rawproduct.Donor;
import com.vem.atsecserver.entity.rawproduct.DonorInstitute;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.sales.CustomerService;
import com.vem.atsecserver.service.rawproduct.*;
import com.vem.atsecserver.service.user.PermissionService;
import com.vem.atsecserver.service.user.RoleService;
import com.vem.atsecserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private DonorService donorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RawProductService rawProductService;

    @Autowired
    private TissueService tissueService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DonorInstituteService donorInstituteService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Permission readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE", "READ_PRIVILEGE");
        Permission writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE", "WRITE_PRIVILEGE");

        List<Permission> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", "ROLE_USER", Arrays.asList(readPrivilege));


        User user = new User();
        user.setName("Volkan");
        user.setSurname("Ulutaş");
        user.setPassword("123");
        user.setUsername("volkanulutas@gmail.com");
        user.setRole(adminRole);
        user.setEnabled(true);
        userService.save(user);

        TissueType tissueType1 = new TissueType("Femur Başı", "Femur Başı");
        tissueType1 = tissueService.create(tissueType1);
        TissueType tissueType2 = new TissueType("Diskol Femur", "Diskol Femur");
        tissueType2 = tissueService.create(tissueType2);

        Location location = new Location("A1", "A1");
        location = locationService.create(location);
        Location location2 = new Location("A2", "A2");
        location2 = locationService.create(location2);
        Location location3 = new Location("B1", "B1");
        location3 = locationService.create(location3);
        Location location4 = new Location("B2", "B2");
        location4 = locationService.create(location4);

        DonorInstitute donorInstitute = new DonorInstitute();
        donorInstitute.setCode("HAC01");
        donorInstitute.setName("Hacettepe Üniversitesi");
        donorInstitute = donorInstituteService.create(donorInstitute);

        RawProduct raw1 = new RawProduct();
        raw1.setDefinition("Açıklama");
        raw1.setStatus(EnumRawProductStatus.ACCEPTING);
        raw1.setInformation("Ek Bilgiler bilgiler");
        raw1.setLocation(location);
        raw1.setTissueType(tissueType1);
        raw1.setArrivalDate(System.currentTimeMillis());
        raw1.setIssueTissueDate(System.currentTimeMillis());
        raw1.setDonorInstitute(donorInstitute);
        raw1.setDeleted(false);
        raw1 = rawProductService.create(raw1);

        RawProduct raw2 = new RawProduct();
        raw2.setDefinition("Açıklama");
        raw2.setStatus(EnumRawProductStatus.QUARANTINE);
        raw2.setInformation("Ek Bilgi");
        raw2.setLocation(location2);
        raw2.setTissueType(tissueType1);
        raw2.setArrivalDate(System.currentTimeMillis());
        raw2.setIssueTissueDate(System.currentTimeMillis());
        raw2.setDonorInstitute(donorInstitute);
        raw2.setDeleted(false);
        raw2 = rawProductService.create(raw2);

        RawProduct raw3 = new RawProduct();
        raw3.setDefinition("Açıklama");
        raw3.setStatus(EnumRawProductStatus.REJECT);
        raw3.setInformation("Ek Bilgi");
        raw3.setLocation(location3);
        raw3.setTissueType(tissueType1);
        raw3.setArrivalDate(System.currentTimeMillis());
        raw3.setIssueTissueDate(System.currentTimeMillis());
        raw3.setDonorInstitute(donorInstitute);
        raw3.setDeleted(false);
        raw3 = rawProductService.create(raw3);

        RawProduct raw4 = new RawProduct();
        raw4.setDefinition("Açıklama");
        raw4.setStatus(EnumRawProductStatus.MEDICAL_WASTE);
        raw4.setInformation("Ek Bilgi");
        raw4.setLocation(location4);
        raw4.setTissueType(tissueType1);
        raw4.setArrivalDate(System.currentTimeMillis());
        raw4.setIssueTissueDate(System.currentTimeMillis());
        raw4.setDonorInstitute(donorInstitute);
        raw4.setDeleted(false);
        raw4 = rawProductService.create(raw4);

        Customer customer = new Customer();
        customer.setIdentityNumber("1");
        customer.setAddress("Test Adresi");
        customer.setDefinition("Test Açıklaması");
        customer.setCustomerType(EnumCustomerType.FIRM);
        customer.setName("Test A.Ş.");
        customer.setTelephone("+90(352)3261615");
        customer.setDeleted(false);
        customer = customerService.create(customer);

        Donor donor1 = new Donor();
        donor1.setDeleted(false);
        donor1.setCode("1");
        donor1.setAddress("Donor Adresi");
        donor1.setName("Ali");
        donor1.setSurname("Koca");
        donor1.setTelephone("+90(532)3261615");
        donor1.setRawProducts(Arrays.asList(raw1));
        donor1 = donorService.create(donor1);

        Donor donor2 = new Donor();
        donor2.setDeleted(false);
        donor2.setCode("1");
        donor2.setAddress("Donor Adresi");
        donor2.setName("Volkan");
        donor2.setSurname("Ulutaş");
        donor2.setTelephone("+90(532)3261615");
        donor2.setRawProducts(Arrays.asList(raw2));
        donor2 = donorService.create(donor2);

        Donor donor3 = new Donor();
        donor3.setDeleted(false);
        donor3.setCode("3");
        donor3.setAddress("Donor Adresi");
        donor3.setName("Emine");
        donor3.setSurname("Ulutaş");
        donor3.setTelephone("+90(532)3261615");
        donor3.setRawProducts(Arrays.asList(raw3));
        donor3 = donorService.create(donor3);

        Donor donor4 = new Donor();
        donor4.setDeleted(false);
        donor4.setCode("4");
        donor4.setAddress("Donor Adresi");
        donor4.setName("Ali İhsan");
        donor4.setSurname("Koca");
        donor4.setTelephone("+90(532)3261615");
        donor4.setRawProducts(Arrays.asList(raw4));
        donor4 = donorService.create(donor4);

        raw1.setDonor(donor1);
        rawProductService.update(raw1);

        raw2.setDonor(donor2);
        rawProductService.update(raw2);

        raw3.setDonor(donor3);
        rawProductService.update(raw3);

        raw4.setDonor(donor4);
        rawProductService.update(raw4);


        alreadySetup = true;
    }

    @Transactional
    private Permission createPrivilegeIfNotFound(String name, String definition) {
        return permissionService.create(new Permission(name, definition));
    }

    @Transactional
    private Role createRoleIfNotFound(String name, String definition, Collection<Permission> privileges) {
        Role role = new Role(name, definition);
        role.setPermissions(privileges);
        return roleService.create(role);
    }
}