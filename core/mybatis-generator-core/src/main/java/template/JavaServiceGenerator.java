package template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @Company: 2017-2017 备胎科技
 * @FileName: JavaServiceGenerator
 * @Desctiption:
 * @Author: chenye
 * @Date: Created by 2017/9/19 15:47
 * @Modified Update By:
 */
public class JavaServiceGenerator {

    public void test() {
        Configuration cfg = new Configuration();
        try {
            cfg.setClassForTemplateLoading(JavaServiceGenerator.class, "/template/ftl"); //指定模板所在的classpath目录
            Template t = cfg.getTemplate("ServiceTemplate"); //指定模板
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addJavaServiceGenerator(List<ServiceTemplateEntity> serviceTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (ServiceTemplateEntity s : serviceTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
//                cfg.setClassForTemplateLoading(JavaServiceGenerator.class, "/ftl"); //指定模板所在的classpath目录
                Template t = cfg.getTemplate("ServiceTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + s.getClassName() + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    public static void addJavaBoGenerator(List<DtoTemplateEntity> dtoTemplateEntitylist) {
//        for (int i = 1; i< 5; i++) {
        Configuration cfg = new Configuration();
        try {
            for (DtoTemplateEntity s : dtoTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
//                cfg.setClassForTemplateLoading(JavaServiceGenerator.class, "/ftl"); //指定模板所在的classpath目录
                Template t = cfg.getTemplate("BoTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage()+s.getLessClass());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + s.getLessClass() + "/" + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaDtoGenerator(List<DtoTemplateEntity> dtoTemplateEntitylist) {
//        for (int i = 1; i< 5; i++) {
        Configuration cfg = new Configuration();
        try {
            for (DtoTemplateEntity s : dtoTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
//                cfg.setClassForTemplateLoading(JavaServiceGenerator.class, "/ftl"); //指定模板所在的classpath目录
                Template t = cfg.getTemplate("DtoTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + "/" + className + ".java")); //java文件的生成目录
//                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + s.getLessClass() + "/" + className + ".java")); //java文件的生成目录
                    t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                    fos.flush();
                    fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaFacadeGenerator(List<FacadeTemplateEntity> dtoTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (FacadeTemplateEntity s : dtoTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("FacadeTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaConvertGenerator(List<ConvertTemplateEntity> dtoTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (ConvertTemplateEntity s : dtoTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("ConvertTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaFacadeImplGenerator(List<FacadeImplTemplateEntity> implTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (FacadeImplTemplateEntity s : implTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("FacadeImplTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaServiceTestGenerator(List<ServiceTestTemplateEntity> implTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (ServiceTestTemplateEntity s : implTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("ServiceTestTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaWebControllerGenerator(List<WebControllerTemplateEntity> implTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (WebControllerTemplateEntity s : implTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("WebControllerTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                System.out.println("【"+f.getAbsoluteFile()+"】");
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaWebServiceGenerator(List<WebServiceTemplateEntity> implTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (WebServiceTemplateEntity s : implTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("WebServiceTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                System.out.println("【"+f.getAbsoluteFile()+"】");
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void addJavaFacadeImplTestGenerator(List<FacadeImplTestTemplateEntity> implTemplateEntitylist) {
        Configuration cfg = new Configuration();
        try {
            for (FacadeImplTestTemplateEntity s : implTemplateEntitylist) {
                cfg.setDirectoryForTemplateLoading(new File("D:\\work-ayhuli\\workspace\\generator\\core\\mybatis-generator-core\\src\\main\\java\\ftl"));
                Template t = cfg.getTemplate("FacadeImplTestTemplate.ftl"); //指定模板
                File f = new File(s.getProjectTargetPackage());
                if (!f.mkdirs()) {
//                    System.out.println("失败" + f.getAbsolutePath());
                }
//                System.out.println(System.getProperty("user.dir"));
                String className = s.getClassName();
//                className = className.replace("001", "00" + i);
                FileOutputStream fos = new FileOutputStream(new File(s.getProjectTargetPackage() + className + ".java")); //java文件的生成目录
                t.process(s, new OutputStreamWriter(fos, "utf-8")); //
                fos.flush();
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void main(String[] args) {
        JavaServiceGenerator javaServiceGenerator = new JavaServiceGenerator();
        javaServiceGenerator.test();
    }
}
