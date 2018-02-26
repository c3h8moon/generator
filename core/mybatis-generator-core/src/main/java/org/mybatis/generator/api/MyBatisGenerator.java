/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.api;

import static org.mybatis.generator.internal.util.ClassloaderUtility.getCustomClassloader;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.XmlFileMergerJaxp;
import template.*;

/**
 * This class is the main interface to MyBatis generator. A typical execution of the tool involves these steps:
 * 
 * <ol>
 * <li>Create a Configuration object. The Configuration can be the result of a parsing the XML configuration file, or it
 * can be created solely in Java.</li>
 * <li>Create a MyBatisGenerator object</li>
 * <li>Call one of the generate() methods</li>
 * </ol>
 *
 * @author Jeff Butler
 * @see org.mybatis.generator.config.xml.ConfigurationParser
 */
public class MyBatisGenerator {

    public static Map<String, String> lean = new HashMap<String, String>();

    /**
     * The configuration.
     */
    private Configuration configuration;

    /**
     * The shell callback.
     */
    private ShellCallback shellCallback;

    /**
     * The generated java files.
     */
    private List<GeneratedJavaFile> generatedJavaFiles;

    /**
     * The generated xml files.
     */
    private List<GeneratedXmlFile> generatedXmlFiles;

    /**
     * The warnings.
     */
    private List<String> warnings;

    /**
     * The projects.
     */
    private Set<String> projects;

    /**
     * Constructs a MyBatisGenerator object.
     *
     * @param configuration The configuration for this invocation
     * @param shellCallback an instance of a ShellCallback interface. You may specify
     *                      <code>null</code> in which case the DefaultShellCallback will
     *                      be used.
     * @param warnings      Any warnings generated during execution will be added to this
     *                      list. Warnings do not affect the running of the tool, but they
     *                      may affect the results. A typical warning is an unsupported
     *                      data type. In that case, the column will be ignored and
     *                      generation will continue. You may specify <code>null</code> if
     *                      you do not want warnings returned.
     * @throws InvalidConfigurationException if the specified configuration is invalid
     */
    public MyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List<String> warnings)
            throws InvalidConfigurationException {
        super();
        if (configuration == null) {
            throw new IllegalArgumentException(getString("RuntimeError.2")); //$NON-NLS-1$
        } else {
            this.configuration = configuration;
        }

        if (shellCallback == null) {
            this.shellCallback = new DefaultShellCallback(false);
        } else {
            this.shellCallback = shellCallback;
        }

        if (warnings == null) {
            this.warnings = new ArrayList<String>();
        } else {
            this.warnings = warnings;
        }
        generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
        projects = new HashSet<String>();

        this.configuration.validate();
    }

    /**
     * This is the main method for generating code. This method is long running, but progress can be provided and the
     * method can be canceled through the ProgressCallback interface. This version of the method runs all configured
     * contexts.
     *
     * @param callback an instance of the ProgressCallback interface, or <code>null</code> if you do not require progress
     *                 information
     * @throws SQLException         the SQL exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback) throws SQLException, IOException, InterruptedException {
        generate(callback, null, null, true);
    }

    /**
     * This is the main method for generating code. This method is long running, but progress can be provided and the
     * method can be canceled through the ProgressCallback interface.
     *
     * @param callback   an instance of the ProgressCallback interface, or <code>null</code> if you do not require progress
     *                   information
     * @param contextIds a set of Strings containing context ids to run. Only the contexts with an id specified in this list
     *                   will be run. If the list is null or empty, than all contexts are run.
     * @throws SQLException         the SQL exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds)
            throws SQLException, IOException, InterruptedException {
        generate(callback, contextIds, null, true);
    }

    /**
     * This is the main method for generating code. This method is long running, but progress can be provided and the
     * method can be cancelled through the ProgressCallback interface.
     *
     * @param callback                 an instance of the ProgressCallback interface, or <code>null</code> if you do not require progress
     *                                 information
     * @param contextIds               a set of Strings containing context ids to run. Only the contexts with an id specified in this list
     *                                 will be run. If the list is null or empty, than all contexts are run.
     * @param fullyQualifiedTableNames a set of table names to generate. The elements of the set must be Strings that exactly match what's
     *                                 specified in the configuration. For example, if table name = "foo" and schema = "bar", then the fully
     *                                 qualified table name is "foo.bar". If the Set is null or empty, then all tables in the configuration
     *                                 will be used for code generation.
     * @throws SQLException         the SQL exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames)
            throws SQLException, IOException, InterruptedException {
        generate(callback, contextIds, fullyQualifiedTableNames, true);
    }

    /**
     * This is the main method for generating code. This method is long running, but progress can be provided and the
     * method can be cancelled through the ProgressCallback interface.
     *
     * @param callback                 an instance of the ProgressCallback interface, or <code>null</code> if you do not require progress
     *                                 information
     * @param contextIds               a set of Strings containing context ids to run. Only the contexts with an id specified in this list
     *                                 will be run. If the list is null or empty, than all contexts are run.
     * @param fullyQualifiedTableNames a set of table names to generate. The elements of the set must be Strings that exactly match what's
     *                                 specified in the configuration. For example, if table name = "foo" and schema = "bar", then the fully
     *                                 qualified table name is "foo.bar". If the Set is null or empty, then all tables in the configuration
     *                                 will be used for code generation.
     * @param writeFiles               if true, then the generated files will be written to disk.  If false,
     *                                 then the generator runs but nothing is written
     * @throws SQLException         the SQL exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames,
            boolean writeFiles) throws SQLException, IOException, InterruptedException {

        if (callback == null) {
            callback = new NullProgressCallback();
        }

        generatedJavaFiles.clear();
        generatedXmlFiles.clear();
        ObjectFactory.reset();
        RootClassInfo.reset();

        // calculate the contexts to run
        List<Context> contextsToRun;
        if (contextIds == null || contextIds.size() == 0) {
            contextsToRun = configuration.getContexts();
        } else {
            contextsToRun = new ArrayList<Context>();
            for (Context context : configuration.getContexts()) {
                if (contextIds.contains(context.getId())) {
                    contextsToRun.add(context);
                }
            }
        }

        // setup custom classloader if required
        if (configuration.getClassPathEntries().size() > 0) {
            ClassLoader classLoader = getCustomClassloader(configuration.getClassPathEntries());
            ObjectFactory.addExternalClassLoader(classLoader);
        }

        // now run the introspections...
        int totalSteps = 0;
        for (Context context : contextsToRun) {
            totalSteps += context.getIntrospectionSteps();
        }
        callback.introspectionStarted(totalSteps);

        for (Context context : contextsToRun) {
            context.introspectTables(callback, warnings, fullyQualifiedTableNames);
        }

        // now run the generates
        totalSteps = 0;
        for (Context context : contextsToRun) {
            totalSteps += context.getGenerationSteps();
        }
        callback.generationStarted(totalSteps);

        for (Context context : contextsToRun) {
            context.generateFiles(callback, generatedJavaFiles, generatedXmlFiles, warnings);
        }

        // now save the files
        if (writeFiles) {
            callback.saveStarted(generatedXmlFiles.size() + generatedJavaFiles.size());

            for (GeneratedXmlFile gxf : generatedXmlFiles) {
                //                gxf.getDocument().getRootElement();
                projects.add(gxf.getTargetProject());
                writeGeneratedXmlFile(gxf, callback);
            }

            for (GeneratedJavaFile gjf : generatedJavaFiles) {
                //                System.out.println("gjf" + gjf.getFileName());
                //                System.out.println("gjf content" + gjf.getFormattedContent());
                projects.add(gjf.getTargetProject());
                writeGeneratedJavaFile(gjf, callback);
            }

            for (String project : projects) {
                shellCallback.refreshProject(project);
            }
        }
        JavaServiceGenerator.addJavaServiceGenerator(assignmentServiceTemplateEntity());
        //        JavaServiceGenerator.addJavaBoGenerator(assignmentBoTemplateEntity());
        JavaServiceGenerator.addJavaDtoGenerator(assignmentDtoTemplateEntity());
        JavaServiceGenerator.addJavaFacadeGenerator(assignmentFacadeTemplateEntity());
        JavaServiceGenerator.addJavaConvertGenerator(assignmentConvertTemplateEntity());
        JavaServiceGenerator.addJavaFacadeImplGenerator(assignmentFacadeImplTemplateEntity());
        JavaServiceGenerator.addJavaFacadeImplTestGenerator(assignmentFacadeImplTestTemplateEntity());
        JavaServiceGenerator.addJavaServiceTestGenerator(assignmentServiceTestTemplateEntity());
        JavaServiceGenerator.addJavaWebControllerGenerator(assignmentWebControllerTemplateEntity());
        JavaServiceGenerator.addJavaWebServiceGenerator(assignmentWebServiceTemplateEntity());
        callback.done();
    }

    private void writeGeneratedJavaFile(GeneratedJavaFile gjf, ProgressCallback callback)
            throws InterruptedException, IOException {
        File targetFile;
        String source;
        try {
            File directory = shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
            targetFile = new File(directory, gjf.getFileName());
            if (targetFile.exists()) {
                if (shellCallback.isMergeSupported()) {
                    source = shellCallback.mergeJavaFile(gjf.getFormattedContent(), targetFile,
                            MergeConstants.OLD_ELEMENT_TAGS, gjf.getFileEncoding());
                } else if (shellCallback.isOverwriteEnabled()) {
                    source = gjf.getFormattedContent();
                    warnings.add(getString("Warning.11", //$NON-NLS-1$
                            targetFile.getAbsolutePath()));
                } else {
                    source = gjf.getFormattedContent();
                    targetFile = getUniqueFileName(directory, gjf.getFileName());
                    warnings.add(getString("Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
                }
            } else {
                source = gjf.getFormattedContent();
            }

            callback.checkCancel();
            callback.startTask(getString("Progress.15", targetFile.getName())); //$NON-NLS-1$
            writeFile(targetFile, source, gjf.getFileEncoding());
        } catch (ShellException e) {
            warnings.add(e.getMessage());
        }
    }

    private void writeGeneratedXmlFile(GeneratedXmlFile gxf, ProgressCallback callback)
            throws InterruptedException, IOException {
        File targetFile;
        String source;
        try {
            File directory = shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
            targetFile = new File(directory, gxf.getFileName());
            if (targetFile.exists()) {
                if (gxf.isMergeable()) {
                    source = XmlFileMergerJaxp.getMergedSource(gxf, targetFile);
                } else if (shellCallback.isOverwriteEnabled()) {
                    source = gxf.getFormattedContent();
                    warnings.add(getString("Warning.11", //$NON-NLS-1$
                            targetFile.getAbsolutePath()));
                } else {
                    source = gxf.getFormattedContent();
                    targetFile = getUniqueFileName(directory, gxf.getFileName());
                    warnings.add(getString("Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
                }
            } else {
                source = gxf.getFormattedContent();
            }

            callback.checkCancel();
            callback.startTask(getString("Progress.15", targetFile.getName())); //$NON-NLS-1$
            writeFile(targetFile, source, "UTF-8"); //$NON-NLS-1$
        } catch (ShellException e) {
            warnings.add(e.getMessage());
        }
    }

    /**
     * Writes, or overwrites, the contents of the specified file.
     *
     * @param file         the file
     * @param content      the content
     * @param fileEncoding the file encoding
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    /**
     * Gets the unique file name.
     *
     * @param directory the directory
     * @param fileName  the file name
     * @return the unique file name
     */
    private File getUniqueFileName(File directory, String fileName) {
        File answer = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if (answer == null) {
            throw new RuntimeException(getString("RuntimeError.3", directory.getAbsolutePath())); //$NON-NLS-1$
        }

        return answer;
    }

    /**
     * Returns the list of generated Java files after a call to one of the generate methods.
     * This is useful if you prefer to process the generated files yourself and do not want
     * the generator to write them to disk.
     *
     * @return the list of generated Java files
     */
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        return generatedJavaFiles;
    }

    /**
     * Returns the list of generated XML files after a call to one of the generate methods.
     * This is useful if you prefer to process the generated files yourself and do not want
     * the generator to write them to disk.
     *
     * @return the list of generated XML files
     */
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        return generatedXmlFiles;
    }

    public List<ServiceTemplateEntity> assignmentServiceTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<ServiceTemplateEntity> serviceTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaServiceGeneratorConfiguration jgc = c.getJavaServiceGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                ServiceTemplateEntity serviceTemplateEntity = new ServiceTemplateEntity();
                serviceTemplateEntity.setClassName(t.getDomainObjectName() + "Service");
                serviceTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Service");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                serviceTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                serviceTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                serviceTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                serviceTemplateEntity.setMapperName("dao");
                serviceTemplateEntity.setBoClazz(t.getDomainObjectName());
                serviceTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                serviceTemplateEntity.setMapperPackage(c.getJavaClientGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dao");
                serviceTemplateEntity.setBoPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());

                serviceTemplateEntity.setDtoClassName(t.getDomainObjectName() + "Dto");
                serviceTemplateEntity.setLessDtoClassName(captureName(t.getDomainObjectName()) + "Dto");
                serviceTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");

                serviceTemplateEntities.add(serviceTemplateEntity);
            }
        }
        return serviceTemplateEntities;
    }

    public List<DtoTemplateEntity> assignmentBoTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<DtoTemplateEntity> dtoTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaBoGeneratorConfiguration jgc = c.getJavaBoGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                for (int i = 1; i < 7; i++) {
                    DtoTemplateEntity dtoTemplateEntity = new DtoTemplateEntity();
                    dtoTemplateEntity.setClassName(t.getDomainObjectName() + i + "00" + "Dto");
                    dtoTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                    dtoTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + i + "00Dto");
                    String projectTargetPackage = jgc.getTargetProject() + "/"
                            + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                    dtoTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                    dtoTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                    dtoTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                    dtoTemplateEntity.setMapperName("dao");
                    dtoTemplateEntity.setBoClazz(t.getDomainObjectName());
                    dtoTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                    dtoTemplateEntity.setMapperPackage(c.getJavaClientGeneratorConfiguration().getTargetPackage() + "."
                            + t.getDomainObjectName() + "Dao");
                    dtoTemplateEntity.setBoPackage(
                            c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                    dtoTemplateEntity.setFields(lean.get(t.getDomainObjectName()));
                    dtoTemplateEntities.add(dtoTemplateEntity);
                }

            }
        }
        return dtoTemplateEntities;
    }

    public static final Map<String, String> dtoPackage = new HashMap<>();

    public List<DtoTemplateEntity> assignmentDtoTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<DtoTemplateEntity> dtoTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaDtoGeneratorConfiguration jgc = c.getJavaDtoGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                DtoTemplateEntity dtoTemplateEntity = new DtoTemplateEntity();
                dtoTemplateEntity.setClassName(t.getDomainObjectName() + "Dto");
                dtoTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Dto");
                dtoTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                dtoTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                dtoTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                dtoTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                dtoTemplateEntity.setMapperName("dao");
                dtoTemplateEntity.setBoClazz(t.getDomainObjectName());
                dtoTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                //                dtoTemplateEntity.setMapperPackage(c.getJavaClientGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Dao");
                dtoTemplateEntity.setBoPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                dtoTemplateEntity.setFields(lean.get(t.getDomainObjectName()));
                dtoTemplateEntities.add(dtoTemplateEntity);
                //                    System.out.println("put key : " + t.getDomainObjectName() + "Dao");
                //                    System.out.println("put value : " + jgc.getTargetPackage() + "." + t.getDomainObjectName() + "Dto");
                dtoPackage.put(t.getDomainObjectName() + "Dao",
                        jgc.getTargetPackage() + "." + t.getDomainObjectName() + "Dto");
            }
        }
        return dtoTemplateEntities;
    }

    public List<FacadeTemplateEntity> assignmentFacadeTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<FacadeTemplateEntity> facadeTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaFacadeGeneratorConfiguration jgc = c.getJavaFacadeGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                FacadeTemplateEntity facadeTemplateEntity = new FacadeTemplateEntity();
                facadeTemplateEntity.setClassName(t.getDomainObjectName() + "Facade");
                facadeTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                facadeTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Facade");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                facadeTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                facadeTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                facadeTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setMapperName("dao");
                facadeTemplateEntity.setBoClazz(t.getDomainObjectName());
                facadeTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                facadeTemplateEntity.setMapperPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");

                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());

                facadeTemplateEntities.add(facadeTemplateEntity);

            }
        }
        return facadeTemplateEntities;
    }

    public List<ConvertTemplateEntity> assignmentConvertTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<ConvertTemplateEntity> facadeTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaConvertGeneratorConfiguration jgc = c.getJavaConvertGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                ConvertTemplateEntity facadeTemplateEntity = new ConvertTemplateEntity();
                facadeTemplateEntity.setClassName(t.getDomainObjectName() + "Convert");
                facadeTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                facadeTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Facade");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                facadeTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                facadeTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                facadeTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setMapperName("dao");
                facadeTemplateEntity.setBoClazz(t.getDomainObjectName());
                facadeTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                facadeTemplateEntity.setMapperPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());

                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");

                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());

                facadeTemplateEntities.add(facadeTemplateEntity);

            }
        }
        return facadeTemplateEntities;
    }

    public List<FacadeImplTemplateEntity> assignmentFacadeImplTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<FacadeImplTemplateEntity> facadeTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaFacadeImplGeneratorConfiguration jgc = c.getJavaFacadeImplGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                FacadeImplTemplateEntity facadeTemplateEntity = new FacadeImplTemplateEntity();
                facadeTemplateEntity.setClassName(t.getDomainObjectName() + "FacadeImpl");
                facadeTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                facadeTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Facade");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                facadeTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                facadeTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                facadeTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setMapperName("dao");
                facadeTemplateEntity.setBoClazz(t.getDomainObjectName());
                facadeTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                facadeTemplateEntity.setMapperPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");
                facadeTemplateEntity.setServicePackage(c.getJavaServiceGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Service");
                facadeTemplateEntity.setFacadePackage(c.getJavaFacadeGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Facade");
                facadeTemplateEntity.setConvertPackage(c.getJavaConvertGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Convert");
                facadeTemplateEntity.setServiceLessClassName(captureName(t.getDomainObjectName()) + "Service");
                facadeTemplateEntity.setServiceClassName(t.getDomainObjectName() + "Service");
                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                //                facadeTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName().toLowerCase());

                facadeTemplateEntities.add(facadeTemplateEntity);

            }
        }
        return facadeTemplateEntities;
    }

    public List<FacadeImplTestTemplateEntity> assignmentFacadeImplTestTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<FacadeImplTestTemplateEntity> facadeTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaFacadeImplTestGeneratorConfiguration jgc = c.getJavaFacadeImplTestGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                FacadeImplTestTemplateEntity facadeTemplateEntity = new FacadeImplTestTemplateEntity();
                facadeTemplateEntity.setClassName(t.getDomainObjectName() + "FacadeTest");
                facadeTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                facadeTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "FacadeTest");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                facadeTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                facadeTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                facadeTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setMapperName("dao");
                facadeTemplateEntity.setBoClazz(t.getDomainObjectName());
                facadeTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                facadeTemplateEntity.setMapperPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");
                facadeTemplateEntity.setServicePackage(c.getJavaServiceGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Service");
                facadeTemplateEntity.setFacadePackage(c.getJavaFacadeGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Facade");
                facadeTemplateEntity.setConvertPackage(c.getJavaConvertGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Convert");
                facadeTemplateEntity.setServiceLessClassName(captureName(t.getDomainObjectName()) + "Service");
                facadeTemplateEntity.setServiceClassName(t.getDomainObjectName() + "Service");
                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                facadeTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());

                facadeTemplateEntity.setFacadeClassName(t.getDomainObjectName() + "Facade");
                facadeTemplateEntity.setFacadeLessClassName(captureName(t.getDomainObjectName()) + "Facade");

                facadeTemplateEntities.add(facadeTemplateEntity);

            }
        }
        return facadeTemplateEntities;
    }

    public List<ServiceTestTemplateEntity> assignmentServiceTestTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<ServiceTestTemplateEntity> serviceTestTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaServiceTestGeneratorConfiguration jgc = c.getJavaServiceTestGeneratorConfiguration();
            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                ServiceTestTemplateEntity serviceTestTemplateEntity = new ServiceTestTemplateEntity();
                serviceTestTemplateEntity.setClassName(t.getDomainObjectName() + "ServiceTest");
                serviceTestTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                serviceTestTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "ServiceTest");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                serviceTestTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                serviceTestTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                serviceTestTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                serviceTestTemplateEntity.setMapperName("dao");
                serviceTestTemplateEntity.setBoClazz(t.getDomainObjectName());
                serviceTestTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                serviceTestTemplateEntity.setMapperPackage(
                        c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                serviceTestTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");
                serviceTestTemplateEntity.setServicePackage(c.getJavaServiceGeneratorConfiguration().getTargetPackage()
                        + "." + t.getDomainObjectName() + "Service");
                serviceTestTemplateEntity.setFacadePackage(c.getJavaFacadeGeneratorConfiguration().getTargetPackage()
                        + "." + t.getDomainObjectName() + "Facade");
                serviceTestTemplateEntity.setConvertPackage(c.getJavaConvertGeneratorConfiguration().getTargetPackage()
                        + "." + t.getDomainObjectName() + "Convert");
                serviceTestTemplateEntity.setServiceLessClassName(captureName(t.getDomainObjectName()) + "Service");
                serviceTestTemplateEntity.setServiceClassName(t.getDomainObjectName() + "Service");
                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                serviceTestTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());
                //                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName().toLowerCase() + "Dto");;

                serviceTestTemplateEntities.add(serviceTestTemplateEntity);

            }
        }
        return serviceTestTemplateEntities;
    }

    public List<WebControllerTemplateEntity> assignmentWebControllerTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<WebControllerTemplateEntity> webControllerTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaWebControllerGeneratorConfiguration jgc = c.getJavaWebControllerGeneratorConfiguration();

            JavaWebServiceGeneratorConfiguration service = c.getJavaWebServiceGeneratorConfiguration();

            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                WebControllerTemplateEntity webControllerTemplateEntity = new WebControllerTemplateEntity();
                webControllerTemplateEntity.setClassName(t.getDomainObjectName() + "Controller");
                webControllerTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                webControllerTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Controller");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                webControllerTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                webControllerTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                //                webControllerTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                //                webControllerTemplateEntity.setMapperName("dao");
                webControllerTemplateEntity.setBoClazz(t.getDomainObjectName());
                webControllerTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                //                webControllerTemplateEntity.setMapperPackage(c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                webControllerTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");
                //                webControllerTemplateEntity.setServicePackage(c.getJavaServiceGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Service");
                //                webControllerTemplateEntity.setFacadePackage(c.getJavaFacadeGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Facade");
                //                webControllerTemplateEntity.setConvertPackage(c.getJavaConvertGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Convert");
                //                webControllerTemplateEntity.setServiceLessClassName(captureName(t.getDomainObjectName()) + "Service");
                //                webControllerTemplateEntity.setServiceClassName(t.getDomainObjectName() + "Service");
                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                webControllerTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());
                //                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName().toLowerCase() + "Dto");;
                webControllerTemplateEntity.setServicePackage(service.getTargetPackage());
                String[] modelNames = jgc.getTargetPackage().split("\\.");

                String model = modelNames[modelNames.length - 2];
                String urlPrefix = model + "/" + captureName(t.getDomainObjectName());
                String lastPackageName = "modules";
                String viewPrefix = model + "/" + captureName(t.getDomainObjectName());
                String permissionPrefix = model + ":" + captureName(t.getDomainObjectName());

                webControllerTemplateEntity.setUrlPrefix(urlPrefix);
                webControllerTemplateEntity.setLastPackageName(lastPackageName);
                webControllerTemplateEntity.setViewPrefix(viewPrefix);
                webControllerTemplateEntity.setPermissionPrefix(permissionPrefix);

                webControllerTemplateEntities.add(webControllerTemplateEntity);

            }
        }
        return webControllerTemplateEntities;
    }

    public List<WebServiceTemplateEntity> assignmentWebServiceTemplateEntity() {
        List<Context> contexts = configuration.getContexts();
        List<WebServiceTemplateEntity> webServiceTemplateEntities = new ArrayList<>();
        for (Context c : contexts) {
            JavaWebServiceGeneratorConfiguration jgc = c.getJavaWebServiceGeneratorConfiguration();

            JavaFacadeGeneratorConfiguration facade = c.getJavaFacadeGeneratorConfiguration();

            if (jgc == null)
                break;
            List<TableConfiguration> tableConfigurations = c.getTableConfigurations();
            for (TableConfiguration t : tableConfigurations) {
                WebServiceTemplateEntity webServiceTemplateEntity = new WebServiceTemplateEntity();
                webServiceTemplateEntity.setClassName(t.getDomainObjectName() + "Service");
                webServiceTemplateEntity.setLessClass(t.getDomainObjectName().toLowerCase());
                webServiceTemplateEntity.setClassNameLess(captureName(t.getDomainObjectName()) + "Service");
                String projectTargetPackage = jgc.getTargetProject() + "/"
                        + jgc.getTargetPackage().replaceAll("\\.", "/") + "/";
                webServiceTemplateEntity.setProjectTargetPackage(projectTargetPackage);
                webServiceTemplateEntity.setTemplatePackage(jgc.getTargetPackage());
                //                webControllerTemplateEntity.setMapperType(t.getDomainObjectName() + "Dao");
                //                webControllerTemplateEntity.setMapperName("dao");
                webServiceTemplateEntity.setBoClazz(t.getDomainObjectName());
                webServiceTemplateEntity.setBoClazzLess(captureName(t.getDomainObjectName()));
                //                webControllerTemplateEntity.setMapperPackage(c.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName());
                webServiceTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName() + "Dto");
                //                webControllerTemplateEntity.setServicePackage(c.getJavaServiceGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Service");
                //                webControllerTemplateEntity.setFacadePackage(c.getJavaFacadeGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Facade");
                //                webControllerTemplateEntity.setConvertPackage(c.getJavaConvertGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName() + "Convert");
                //                webControllerTemplateEntity.setServiceLessClassName(captureName(t.getDomainObjectName()) + "Service");
                //                webControllerTemplateEntity.setServiceClassName(t.getDomainObjectName() + "Service");
                //                facadeTemplateEntity.setBoClassName(t.getDomainObjectName() + "Dao");
                webServiceTemplateEntity.setBoPackage(c.getJavaBoGeneratorConfiguration().getTargetPackage() + "."
                        + t.getDomainObjectName().toLowerCase());
                //                facadeTemplateEntity.setDtoPackage(c.getJavaDtoGeneratorConfiguration().getTargetPackage() + "." + t.getDomainObjectName().toLowerCase() + "Dto");;
                webServiceTemplateEntity.setFacadePackage(facade.getTargetPackage());

                String[] modelNames = jgc.getTargetPackage().split("\\.");

                String model = modelNames[modelNames.length - 2];
                String urlPrefix = model + "/" + captureName(t.getDomainObjectName());
                String lastPackageName = "modules";
                String viewPrefix = model + "/" + captureName(t.getDomainObjectName());
                String permissionPrefix = model + ":" + captureName(t.getDomainObjectName());

                webServiceTemplateEntity.setUrlPrefix(urlPrefix);
                webServiceTemplateEntity.setLastPackageName(lastPackageName);
                webServiceTemplateEntity.setViewPrefix(viewPrefix);
                webServiceTemplateEntity.setPermissionPrefix(permissionPrefix);

                webServiceTemplateEntities.add(webServiceTemplateEntity);

            }
        }
        return webServiceTemplateEntities;
    }

    public static String captureName(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;

    }
}
