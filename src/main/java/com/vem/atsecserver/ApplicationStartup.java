package com.vem.atsecserver;

import com.vem.atsecserver.entity.product.EnumProductPreProcessingType;
import com.vem.atsecserver.entity.product.EnumProductStatus;
import com.vem.atsecserver.entity.product.EnumProductType;
import com.vem.atsecserver.entity.product.Product;
import com.vem.atsecserver.entity.rawproduct.*;
import com.vem.atsecserver.entity.sales.Customer;
import com.vem.atsecserver.entity.sales.EnumCustomerType;
import com.vem.atsecserver.entity.user.Permission;
import com.vem.atsecserver.entity.user.Role;
import com.vem.atsecserver.entity.user.User;
import com.vem.atsecserver.service.product.ProductService;
import com.vem.atsecserver.service.rawproduct.*;
import com.vem.atsecserver.service.sales.CustomerService;
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
    private ProductService productService;

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

        /*
        Permission readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE", "READ_PRIVILEGE");
        Permission writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE", "WRITE_PRIVILEGE");

        List<Permission> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", "ROLE_USER", Arrays.asList(readPrivilege));
*/
        createRoles();

        User user = new User();
        user.setName("Ela");
        user.setSurname("Esmer");
        user.setPassword("123");
        user.setUsername("elaesmer@gmail.com");
        user.setRole(roleService.findRoleByName("ROLE_ADMIN"));
        user.setEnabled(true);
        userService.save(user);

        User user2 = new User();
        user2.setName("Volkan");
        user2.setSurname("Ulutaş");
        user2.setPassword("123");
        user2.setUsername("volkanulutas@gmail.com");
        user2.setRole(roleService.findRoleByName("ROLE_USER"));
        user2.setEnabled(true);
        userService.save(user2);

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
        raw1.setId(System.currentTimeMillis());
        raw1.setDefinition("Açıklama");
        raw1.setStatus(EnumRawProductStatus.ACCEPTED);
        raw1.setInformation("Ek Bilgiler");
        raw1.setLocation(location);
        raw1.setTissueType(tissueType1);
        raw1.setArrivalDate(System.currentTimeMillis());
        raw1.setIssueTissueDate(System.currentTimeMillis());
        raw1.setDonorInstitute(donorInstitute);
        raw1.setCheckedOutBy(user);
        raw1.setDeleted(false);
        raw1 = rawProductService.create(raw1);

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
        donor1.setCitizenshipNumber("20403320954");
        donor1.setDeleted(false);
        donor1.setCode("1");
        donor1.setAddress("Donor Adresi");
        donor1.setName("Ali");
        donor1.setSurname("Koca");
        donor1.setTelephone("+90(532)3261615");
        donor1.setRawProducts(Arrays.asList(raw1));
        donor1 = donorService.create(donor1);

        raw1.setDonor(donor1);
        rawProductService.update(raw1);


        Product product = new Product();
        product.setDeleted(false);
        product.setDonor(donor1);
        product.setPreProcessingType(Arrays.asList(EnumProductPreProcessingType.CUTTING,
                EnumProductPreProcessingType.TAKING_CARTILAGE,
                EnumProductPreProcessingType.WASHING));
        product.setCustomer(customer);
        product.setSecCode("12345");
        product.setType(EnumProductType.NONE);
        product.setStatus(EnumProductStatus.COURSE_GRINDING);
        product.setDefinition("Ürün 1");
        product.setStatus(EnumProductStatus.PRE_PROCESSING);
        product.setInformation("Ürün Bilgisi");
        productService.create(product);


        alreadySetup = true;
    }

    @Transactional
    private Permission createPrivilegeIfNotFound(String name, String definition, String menu) {
        return permissionService.create(new Permission(name, definition, menu));
    }

    @Transactional
    private Role createRoleIfNotFound(String name, String definition, Collection<Permission> privileges) {
        Role role = new Role(name, definition);
        role.setPermissions(privileges);
        return roleService.create(role);
    }

    public void createRoles() {
        Permission userPage = createPrivilegeIfNotFound("USER_PAGE_PERMISSION", "USER_PERMISSION", "USER_MENU_PERMISSION");
        Permission rolePage = createPrivilegeIfNotFound("ROLE_PAGE_PERMISSION", "ROLE_PAGE_PERMISSION","USER_MENU_PERMISSION");
        Permission permissionPage = createPrivilegeIfNotFound("PERMISSION_PAGE_PERMISSION", "PERMISSION_PAGE_PERMISSION","USER_MENU_PERMISSION");

        Permission rawProductPage = createPrivilegeIfNotFound("RAWPRODUCT_PAGE_PERMISSION", "RAWPRODUCT_PAGE_PERMISSION","RAWPRODUCT_MENU_PERMISSION");
        Permission rawProductRejectPage = createPrivilegeIfNotFound("RAWPRODUCTREJECT_PAGE_PERMISSION", "RAWPRODUCTREJECT_PAGE_PERMISSION", "RAWPRODUCT_MENU_PERMISSION");
        Permission donorPage = createPrivilegeIfNotFound("DONOR_PAGE_PERMISSION", "DONOR_PAGE_PERMISSION", "RAWPRODUCT_MENU_PERMISSION");

        Permission productPage = createPrivilegeIfNotFound("PRODUCT_PAGE_PERMISSION", "PRODUCT_PAGE_PERMISSION", "PRODUCT_MENU_PERMISSION");
        Permission customerPage = createPrivilegeIfNotFound("CUSTOMER_PAGE_PERMISSION", "CUSTOMER_PAGE_PERMISSION", "PRODUCT_MENU_PERMISSION");


        List<Permission> adminPrivileges = Arrays.asList(userPage, rolePage, permissionPage,
                rawProductPage, rawProductRejectPage, donorPage, productPage, customerPage);
        List<Permission> userPrivileges = Arrays.asList(productPage, rolePage, userPage);
        createRoleIfNotFound("ROLE_ADMIN", "ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", "ROLE_USER", userPrivileges);
    }
}