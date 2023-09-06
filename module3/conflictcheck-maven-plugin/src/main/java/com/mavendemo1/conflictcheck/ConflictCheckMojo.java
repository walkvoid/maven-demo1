package com.mavendemo1.conflictcheck;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/9/6
 * @desc 依赖冲突检查插件
 */

@Mojo(name = "conflict-check", defaultPhase = LifecyclePhase.COMPILE)
public class ConflictCheckMojo extends AbstractMojo {


    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("===============> start depends conflict check ============");

    }
}
