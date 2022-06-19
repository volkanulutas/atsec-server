package com.vem.atsecserver;

import com.vem.atsecserver.entity.product.*;
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
import com.vem.atsecserver.service.xml.CityDistrictService;
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

    @Autowired
    private CityDistrictService cityDistrictService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        cityDistrictService.initialize();
        Role isExist = roleService.findRoleByName("ROLE_ADMIN");
        if (isExist != null) {
            return;
        }

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

        Location locationNormal_1 = new Location("K1", "K1", EnumLocationType.NORMAL);
        locationNormal_1 = locationService.create(locationNormal_1);
        Location locationNormal_2 = new Location("K2", "K2", EnumLocationType.NORMAL);
        locationNormal_2 = locationService.create(locationNormal_2);
        Location locationNormal_3 = new Location("K3", "K3", EnumLocationType.NORMAL);
        locationNormal_3 = locationService.create(locationNormal_3);
        Location locationNormal_4 = new Location("K4", "K4", EnumLocationType.NORMAL);
        locationNormal_4 = locationService.create(locationNormal_4);
        Location locationNormal_5 = new Location("K5", "K5", EnumLocationType.NORMAL);
        locationNormal_5 = locationService.create(locationNormal_5);
        Location locationNormal_6 = new Location("K6", "K6", EnumLocationType.NORMAL);
        locationNormal_6 = locationService.create(locationNormal_6);


        Location locationReject_1 = new Location("B1", "B1", EnumLocationType.REJECT);
        locationReject_1 = locationService.create(locationReject_1);
        Location locationReject_2 = new Location("B2", "B2", EnumLocationType.REJECT);
        locationReject_2 = locationService.create(locationReject_2);
        Location locationReject_3 = new Location("B3", "B3", EnumLocationType.REJECT);
        locationReject_3 = locationService.create(locationReject_3);
        Location locationReject_4 = new Location("B4", "B4", EnumLocationType.REJECT);
        locationReject_4 = locationService.create(locationReject_4);
        Location locationReject_5 = new Location("B5", "B5", EnumLocationType.REJECT);
        locationReject_5 = locationService.create(locationReject_5);
        Location locationReject_6 = new Location("B6", "B6", EnumLocationType.REJECT);
        locationReject_6 = locationService.create(locationReject_6);


        Location locationAccept_1 = new Location("A1", "A1", EnumLocationType.ACCEPT);
        locationAccept_1 = locationService.create(locationAccept_1);
        Location locationAccept_2 = new Location("A2", "A2", EnumLocationType.ACCEPT);
        locationAccept_2 = locationService.create(locationAccept_2);
        Location locationAccept_3 = new Location("A3", "A3", EnumLocationType.ACCEPT);
        locationAccept_3 = locationService.create(locationAccept_3);
        Location locationAccept_4 = new Location("A4", "A4", EnumLocationType.ACCEPT);
        locationAccept_4 = locationService.create(locationAccept_4);
        Location locationAccept_5 = new Location("A5", "A5", EnumLocationType.ACCEPT);
        locationAccept_5 = locationService.create(locationAccept_5);
        Location locationAccept_6 = new Location("A6", "A6", EnumLocationType.ACCEPT);
        locationAccept_6 = locationService.create(locationAccept_6);

        DonorInstitute donorInstitute = new DonorInstitute();
        donorInstitute.setCode("H0001");
        donorInstitute.setName("Hacettepe Üniversitesi");
        donorInstitute = donorInstituteService.create(donorInstitute);

        DonorInstitute donorInstitute2 = new DonorInstitute();
        donorInstitute2.setCode("H0002");
        donorInstitute2.setName("Ankara Üniversitesi");
        donorInstitute2 = donorInstituteService.create(donorInstitute2);

        Donor donor1 = new Donor();
        donor1.setName("Volkan");
        donor1.setSurname("Ulutaş");
        donor1.setDonorInstitute(donorInstitute);
        donor1.setCitizenshipNumber("20603320916");
        donor1.setTissueNumber(2);
        donor1.setTissueType("Doku Türü");
        donor1.setBirthdate(System.currentTimeMillis());
        donor1.setBloodType(EnumBloodType.A_POSITIVE);
        donor1.setSex(EnumSex.MALE);
        donor1.setTelephone("05323423327");
        donor1.setAddressDistrict("Çankaya");
        donor1.setAddressCity("Ankara");
        donor1.setAddress("adres");
        donor1 = donorService.create(donor1);

        Donor donor2 = new Donor();
        donor2.setName("Emine");
        donor2.setSurname("Ulutaş");
        donor2.setDonorInstitute(donorInstitute);
        donor2.setCitizenshipNumber("20612320624");
        donor2.setTissueNumber(2);
        donor2.setTissueType("Doku Türü");
        donor2.setBirthdate(System.currentTimeMillis());
        donor2.setBloodType(EnumBloodType.AB_NEGATIVE);
        donor2.setSex(EnumSex.FEMALE);
        donor2.setTelephone("05323423327");
        donor2.setAddressDistrict("Çankaya");
        donor2.setAddressCity("Ankara");
        donor2.setAddress("adres");
        donor2 = donorService.create(donor2);

        RawProduct raw1 = new RawProduct();
        // raw1.setId(System.currentTimeMillis());
        raw1.setDefinition("Açıklama");
        raw1.setStatus(EnumRawProductStatus.ACCEPTED);
        raw1.setInformation("Ek Bilgiler");
        raw1.setDoctorName("doctor1");
        raw1.setLocation(locationNormal_1);
        raw1.setDoctorName("Orhan Yıldız");
        raw1.setSignerInfo("İmzalayıcı");
        raw1.setTissueType(tissueType1);
        raw1.setArrivalDate(System.currentTimeMillis());
        raw1.setIssueTissueDate(System.currentTimeMillis());
        raw1.setCheckedOutBy(user);
        raw1.setDeleted(false);
        raw1.setDonor(donor1);
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

        Product product = new Product();
        product.setDeleted(false);

        product.addPreProcessingType(new PreProcessingType(EnumProductPreProcessingType.CUTTING, System.currentTimeMillis()));
        product.addPreProcessingType(new PreProcessingType(EnumProductPreProcessingType.TAKING_CARTILAGE, System.currentTimeMillis()));
        product.addPreProcessingType(new PreProcessingType(EnumProductPreProcessingType.WASHING, System.currentTimeMillis()));

        product.setCustomer(customer);
        product.setSecCode("1");
        product.setType(EnumProductType.NONE);
        product.setType(EnumProductType.NONE);
        product.setDefinition("Ürün 1");
        product.setStatus(EnumProductStatus.PRE_PROCESSING);
        product.setInformation("Ürün Bilgisi");
        product.setDonor(donor1);
        product = productService.create(product);
        System.out.println("");
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
        Permission userPage = createPrivilegeIfNotFound("USER_PAGE_PERMISSION", "USER_PAGE_PERMISSION", "USER_MENU_PERMISSION");
        Permission rolePage = createPrivilegeIfNotFound("ROLE_PAGE_PERMISSION", "ROLE_PAGE_PERMISSION", "USER_MENU_PERMISSION");
        Permission permissionPage = createPrivilegeIfNotFound("PERMISSION_PAGE_PERMISSION", "PERMISSION_PAGE_PERMISSION", "USER_MENU_PERMISSION");


        Permission userListUpdatePer = createPrivilegeIfNotFound("list-user-update", "list-user-update", "USER_PAGE_PERMISSION");
        /*
        Permission userListDeletePer = createPrivilegeIfNotFound("list-user-delete", "list-user-delete", "USER_PAGE_PERMISSION");
        Permission roleListUpdatePer = createPrivilegeIfNotFound("list-role-update", "list-role-update", "ROLE_PAGE_PERMISSION");
        Permission roleListDeletePer = createPrivilegeIfNotFound("list-role-delete", "list-role-delete", "ROLE_PAGE_PERMISSION");
        Permission permissionListUpdatePer = createPrivilegeIfNotFound("list-permission-update", "list-permission-update", "PERMISSION_PAGE_PERMISSION");
        Permission permissionListDeletePer = createPrivilegeIfNotFound("list-permission-delete", "list-permission-delete", "PERMISSION_PAGE_PERMISSION");
*/

        Permission rawProductPage = createPrivilegeIfNotFound("RAWPRODUCT_PAGE_PERMISSION", "RAWPRODUCT_PAGE_PERMISSION", "RAWPRODUCT_MENU_PERMISSION");
        Permission rawProductRejectPage = createPrivilegeIfNotFound("RAWPRODUCTREJECT_PAGE_PERMISSION", "RAWPRODUCTREJECT_PAGE_PERMISSION", "RAWPRODUCT_MENU_PERMISSION");
        Permission donorPage = createPrivilegeIfNotFound("DONOR_PAGE_PERMISSION", "DONOR_PAGE_PERMISSION", "RAWPRODUCT_MENU_PERMISSION");

        Permission productPage = createPrivilegeIfNotFound("PRODUCT_PAGE_PERMISSION", "PRODUCT_PAGE_PERMISSION", "PRODUCT_MENU_PERMISSION");
        Permission customerPage = createPrivilegeIfNotFound("CUSTOMER_PAGE_PERMISSION", "CUSTOMER_PAGE_PERMISSION", "PRODUCT_MENU_PERMISSION");
        Permission packingPage = createPrivilegeIfNotFound("PACKINGPRODUCT_PAGE_PERMISSION", "PACKINGPRODUCT_PAGE_PERMISSION", "PRODUCT_MENU_PERMISSION");

        List<Permission> adminPrivileges = Arrays.asList(userPage, rolePage, permissionPage,
                rawProductPage, rawProductRejectPage, donorPage,
                productPage, customerPage, packingPage, userListUpdatePer);
        List<Permission> userPrivileges = Arrays.asList(productPage, rolePage, userPage);
        createRoleIfNotFound("ROLE_ADMIN", "ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", "ROLE_USER", userPrivileges);
    }
}