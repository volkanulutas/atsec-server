package com.vem.atsecserver;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.entity.DonorInstitute;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.Location;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.rawproduct.TissueType;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.*;
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

        RawProduct raw = new RawProduct();
        raw.setDefinition("Açıklama");
        raw.setStatus(EnumRawProductStatus.NO_STATUS);
        raw.setInformation("Ek Bilgi");
        raw.setLocation(location);
        raw.setTissueType(tissueType1);
        raw.setArrivalDate(System.currentTimeMillis());
        raw.setIssueTissueDate(System.currentTimeMillis());
        raw.setDonorInstitute(donorInstitute);
        raw.setDeleted(false);
        raw = rawProductService.create(raw);

        Customer customer = new Customer();
        customer.setIdentityNumber("1");
        customer.setAddress("Test Adresi");
        customer.setDefinition("Test Açıklaması");
        customer.setCustomerType(EnumCustomerType.FIRM);
        customer.setName("Test A.Ş.");
        customer.setTelephone("+90(352)3261615");
        customer.setDeleted(false);
        customer = customerService.create(customer);

        Donor donor = new Donor();
        donor.setDeleted(false);
        donor.setCode("1");
        donor.setAddress("Donor Adresi");
        donor.setName("Ali");
        donor.setSurname("Koca");
        donor.setTelephone("+90(532)3261615");
        donor.setRawProducts(Arrays.asList(raw));
        donor = donorService.create(donor);

        raw.setDonor(donor);
        rawProductService.update(raw);



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