package com.qainfotech;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class FetchURLs {
    static String projectPath = System.getProperty("user.dir");
    public static String getURL(String target){
        String url = null;
        try{
            InputStream readYaml = new FileInputStream(new File(projectPath+"/links.yaml"));
            Yaml yaml = new Yaml();
            Map<String, String> yamlObj =(Map<String, String>) yaml.load(readYaml);
            url = yamlObj.get(target);
        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
        }

        return url;
        
    }
}