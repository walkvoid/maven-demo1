package com.mavendemo1.conflictcheck;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.*;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilder;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilderException;
import org.apache.maven.shared.dependency.graph.DependencyNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author walkvoid
 * @version v1.0.0
 * @date 2023/9/6
 * @desc 依赖冲突检查插件
 *  cd .\module2\module2-service\module2-product-service\
 *  mvnDebug com.mavendemo1:conflictcheck-maven-plugin:2.3-SNAPSHOT:conflict-check
 *                                     |--5.3.9========>
 *  org.springframework:spring-core => |
 *                                     |--5.4.9(used)==>
 */

@Mojo(name = "conflict-check", defaultPhase = LifecyclePhase.COMPILE)
public class ConflictCheckMojo extends AbstractMojo {


    @Parameter(name = "authorName", defaultValue = "")
    private String authorName;
    @Parameter(name = "projectBasedir", defaultValue = "${project.basedir}")
    private String projectBasedir;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Component(hint = "default")
    private DependencyGraphBuilder dependencyGraphBuilder;

    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    private MavenSession session;

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = true)
    protected List<MavenProject> reactorProjects;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("===============> start depends conflict check ============");
        getLog().info("===============> authorName:"+authorName+"============");
        getLog().info("===============> projectBasedir:"+projectBasedir+"============");
        List<Dependency> dependencies = project.getDependencies();
        ProjectBuildingRequest projectBuildingRequest = project.getProjectBuildingRequest();

        ArtifactFilter artifactFilter = new ScopeArtifactFilter(Artifact.SCOPE_TEST);
        ProjectBuildingRequest buildingRequest =
                new DefaultProjectBuildingRequest(session.getProjectBuildingRequest());
        buildingRequest.setProject(project);
        DependencyNode dependencyNode = null;
        try {
            dependencyNode = dependencyGraphBuilder.buildDependencyGraph(buildingRequest, artifactFilter);
        } catch (DependencyGraphBuilderException e) {
            throw new RuntimeException(e);
        }
        getLog().info("===============> dependencyNode ArtifactId:"+dependencyNode.getArtifact().getArtifactId()+"============");

//       reactorProjects = {ArrayList@4612}  size = 10 for (Dependency dependency : dependencies) {
//
//            getLog().info("===============> dependency ArtifactId:"+dependency.getArtifactId()+"============");
//            getLog().info("===============> dependency SystemPath:"+dependency.getSystemPath()+"============");
//            if (dependency.getArtifactId().equals("module2-product-api")) {
//                String systemPath = dependency.getSystemPath();
//                getLog().info("===============> module2-product-api pom path:"+systemPath+"============");
//            }
//        }

//        String pomFilePath = "D:\\developer\\apache-maven-3.5.4\\mavenRepository\\com\\mavendemo1\\module2-product-api\\2.3-SNAPSHOT\\module2-product-api-2.3-SNAPSHOT.pom";
//        try {
//            ProjectBuildingResult build = new DefaultProjectBuilder().build(new File(pomFilePath), project.getProjectBuildingRequest());
//        } catch (ProjectBuildingException e) {
//            throw new RuntimeException(e);
//        }

        //        try {
//            ProjectBuildingResult build = new DefaultProjectBuilder().build(new File(pomFilePath), project.getProjectBuildingRequest());
//            getLog().info("===============> module2-product-api:"+build.getProject().getArtifactId()+"============");
//            getLog().info("===============>module2-product-api:"+build.getProject().getGroupId()+"============");
//        } catch (ProjectBuildingException e) {
//            getLog().error("===============>module2-product-api error:"+e.getStackTrace()+"============");
//            //throw new RuntimeException(e);
//        }

    }
}
