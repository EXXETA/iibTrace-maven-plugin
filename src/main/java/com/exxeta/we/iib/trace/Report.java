/*
 * iibTrace maven plugin
 * Copyright (C) 2017 Thomas Pohl and EXXETA AG
 * http://www.exxeta.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.exxeta.we.iib.trace;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "report")
public class Report extends AbstractMojo {

	@Parameter(required = true)
	private String mqsiDir;

	@Parameter(required = true)
	private String broker;

	@Parameter(required = true)
	private String executionGroup;

	@Parameter(defaultValue = "${project.build.directory}/iibTrace")
	private File reportDir;

	@Parameter(defaultValue = "true")
	private boolean clear;

	public void execute() throws MojoExecutionException {
		try {
			getLog().info("Creating trace file. ");

			String cmd = "cmd.exe /c mqsiprofile && mqsireadlog " + broker + " -e " + executionGroup + " -u -o \""
					+ reportDir.getAbsolutePath() + "/" + executionGroup + ".xml\" 2>nul";
			getLog().info("Executing:" + cmd);
			Runtime.getRuntime().exec(cmd, null, new File(mqsiDir)).waitFor();

			getLog().info("Resetting trace to none. ");

			String level = "none";
			cmd = "cmd.exe /c mqsiprofile && mqsichangetrace " + broker + " -u -e " + executionGroup + " -l " + level + " -c 2000 " + (clear ? " -r" : "");
			getLog().info("Executing:" + cmd);
			Runtime.getRuntime().exec(cmd, null, new File(mqsiDir)).waitFor();

		} catch (Exception e) {
			getLog().error(e);
		}
	}
}
