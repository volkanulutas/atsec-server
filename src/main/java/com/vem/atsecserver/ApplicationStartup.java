package com.vem.atsecserver;

import com.vem.atsecserver.entity.Donor;
import com.vem.atsecserver.entity.DonorInstitute;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductStatus;
import com.vem.atsecserver.entity.rawproduct.EnumRawProductType;
import com.vem.atsecserver.entity.rawproduct.RawProduct;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.CustomerService;
import com.vem.atsecserver.service.DonorInstituteService;
import com.vem.atsecserver.service.DonorService;
import com.vem.atsecserver.service.RawProductService;
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


        RawProduct raw = new RawProduct();
        raw.setDefinition("Ürün Açıklaması");
        raw.setLocation("A-1");
        raw.setAcceptanceDate(System.currentTimeMillis());
        raw.setInformation("Ek Bilgi");
        raw.setStatus(EnumRawProductStatus.QUARANTINE);
        raw.setType(EnumRawProductType.NONE);
        raw.setAcceptanceDate(System.currentTimeMillis());
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
        customer.setRawProducts(Arrays.asList(raw));
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

        raw.setCustomer(customer);
        raw.setDonor(donor);
        rawProductService.update(raw);

        DonorInstitute donorInstitute = new DonorInstitute();
        donorInstitute.setCode("HAC01");
        donorInstitute.setName("Hacettepe Üniversitesi");
        donorInstitute = donorInstituteService.create(donorInstitute);

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