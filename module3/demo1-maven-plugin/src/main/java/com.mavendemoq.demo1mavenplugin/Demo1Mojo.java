package com.mavendemoq.demo1mavenplugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @desc
 */
@Mojo(name = "demo1-mojo", defaultPhase = LifecyclePhase.COMPILE)
public class Demo1Mojo extends AbstractMojo {

    @Parameter(name = "authorName", defaultValue = "")
    private String authorName;
    @Parameter(name = "projectBasedir", defaultValue = "${project.basedir}")
    private String projectBasedir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("===============> demo1-mojo start execute ============");
        getLog().info("===============> authorName:"+authorName+"============");
        getLog().info("===============> projectBasedir:"+projectBasedir+"============");
    }
}
