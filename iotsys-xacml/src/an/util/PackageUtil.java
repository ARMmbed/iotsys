/**
 * This class is modified from m-m-m reflection util project, whose website is:
 * http://m-m-m.sourceforge.net/maven/mmm-util/mmm-util-reflect/
 * 
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package an.util;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class PackageUtil {

	private static Logger log = Logger.getLogger(PackageUtil.class.getName());

	public static void findClassesByPackage(String pkgName, boolean includeSubPackages, Set<Class<?>> result)
			throws IOException, ClassNotFoundException {
		Thread.currentThread().setContextClassLoader(PackageUtil.class.getClassLoader());
		StringBuilder qualifiedNameBuilder = new StringBuilder(pkgName);
		qualifiedNameBuilder.append('.');
		String[] classList = { "CombiningAlgorithms", "CommonFunctions", "DateTimeFunctions", "HighOrderBagFunctions",
				"LogicalFunctions", "MatchFunctions", "NumberFunctions", "StringFunctions", "XPathFunctions" };
		log.finer("Be aware of adding new XACML Functions ;-), I hardcoded the functionlist.");

		for (String className : classList) {
			Class<?> clazz = Class.forName(pkgName + "." + className);
			if (!clazz.isAnnotation() && !clazz.isInterface()) {
				result.add(clazz);
			}
		}
	}
}