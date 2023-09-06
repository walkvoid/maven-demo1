package com.mavendemo1.conflictcheck;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.*;

import java.io.File;
import java.util.List;

/**
 * @author walkvoid
 * @version v1.0.0
 * @date 2023/9/6
 * @desc 依赖冲突检查插件
 *   mvnDebug com.mavendemo1:conflictcheck-maven-plugin:2.3-SNAPSHOT:conflict-check
 */

@Mojo(name = "conflict-check", defaultPhase = LifecyclePhase.COMPILE)
public class ConflictCheckMojo extends AbstractMojo {


    @Parameter(name = "authorName", defaultValue = "")
    private String authorName;
    @Parameter(name = "projectBasedir", defaultValue = "${project.basedir}")
    private String projectBasedir;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("===============> start depends conflict check ============");
        getLog().info("===============> authorName:"+authorName+"============");
        getLog().info("===============> projectBasedir:"+projectBasedir+"============");
        List<Dependency> dependencies = project.getDependencies();
        for (Dependency dependency : dependencies) {
            getLog().info("===============> dependency ArtifactId:"+dependency.getArtifactId()+"============");
            getLog().info("===============> dependency SystemPath:"+dependency.getSystemPath()+"============");
//            if (dependency.getArtifactId().equals("module2-product-api")) {
//                String systemPath = dependency.getSystemPath();
//                getLog().info("===============> module2-product-api pom path:"+systemPath+"============");
//            }
        }

        String pomFilePath = "D:\\developer\\apache-maven-3.5.4\\mavenRepository\\com\\mavendemo1\\module2-product-api\\2.3-SNAPSHOT\\module2-product-api-2.3-SNAPSHOT.pom";
        try {
            MavenProject build = new DefaultMavenProjectBuilder().build(new File(pomFilePath), new DefaultProjectBuilderConfiguration());
            getLog().info("===============> module2-product-api:"+build.getArtifactId()+"============");
            getLog().info("===============>module2-product-api:"+build.getGroupId()+"============");
        } catch (ProjectBuildingException e) {
            getLog().error("===============>module2-product-api error:"+e.getStackTrace()+"============");
            //throw new RuntimeException(e);
        }

    }
}
