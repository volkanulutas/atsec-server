package com.vem.atsecserver.service.xml;

import com.vem.atsecserver.data.xml.District;
import com.vem.atsecserver.entity.city.City;
import com.vem.atsecserver.repository.city.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.ClassLoaderUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class CityDistrictService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CityRepository cityRepository;

    private static Map<String, List<District>> cityDistrictMap = new TreeMap<>();

    public static void add(String city, List<District> districtList) {
        cityDistrictMap.put(city, districtList);
    }

    public List<District> getDistricts(String city) {
        return cityDistrictMap.get(city);
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        Iterator<Map.Entry<String, List<District>>> itr = cityDistrictMap.entrySet().iterator();
        while (itr.hasNext()) {
            cities.add(itr.next().getKey().toString());
        }
        return cities;
    }

    public void save(City entity) {
        cityRepository.save(entity);
    }

    public void initialize() {
        try {
            String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("city.xml")).getPath();
            InputStream inputStream = new FileInputStream(filePath);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("CITY");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String cityName = element.getAttribute("cityname");

                    List<District> districtList = new ArrayList<>();
                    for (int j = 0; j < element.getElementsByTagName("DISTRICT").getLength(); j++) {
                        String[] districts = element.getElementsByTagName("DISTRICT").item(j).getTextContent().split("\n");
                        District district = new District();
                        district.setDistrictName(districts[2]);
                        districtList.add(district);
                    }
                    CityDistrictService.add(cityName, districtList);
                }
            }
        } catch (Exception ex) {
            log.error("Dosya okunamadi ", ex);
        }
    }
}