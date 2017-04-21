package cn.xm.yss;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AntUtils {
	private static AntUtils utils;

	private AntUtils() {
	}

	public static AntUtils getInstance() {
		if (utils == null) {
			utils = new AntUtils();
		}
		return utils;
	}

	public void executeTarget(String path, String target) {
		HashMap<String, String> properties = new HashMap<>();
		executeTarget(path, target, properties);
	}

	public void executeTarget(String path, String target, Map<String, String> properties) {
		LinkedList<String> list = new LinkedList<>();
		list.add(target);
		executeTarget(path, list, properties);
	}

	public void executeTarget(String path, LinkedList<String> targets, Map<String, String> properties) {
		// 创建一个默认的监听器,监听项目构建过程中的日志操作
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

		// 创建一个ANT项目
		Project project = new Project();
		project.addBuildListener(consoleLogger);
		try {
			project.fireBuildStarted();
			// 初始化该项目
			project.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// 解析项目的构建文件
			helper.parse(project, new File(path));

			if (!properties.isEmpty()) {
				Set<Map.Entry<String, String>> entryseSet = properties.entrySet();
				for (Map.Entry<String, String> entry : entryseSet) {
					// System.out.println(entry.getKey() + "," +
					// entry.getValue());
					project.setProperty(entry.getKey(), entry.getValue());
				}
			}

			// 执行项目的某一个目标
			if (!targets.isEmpty()) {
				for (String target : targets) {
					project.executeTarget(target);
				}
			}
		} catch (BuildException be) {
			project.fireBuildFinished(be);
		}
	}

}