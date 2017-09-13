/*******************************************************************************
 * Copyright (c) 2001, 2017 IBM Corp. and others
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] http://openjdk.java.net/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 *******************************************************************************/
package com.ibm.jvmti.tests.addToBootstrapClassLoaderSearch;

import com.ibm.jvmti.tests.util.Util;

public class abcl002 {
	
	public String helpAddToBootpathDuringLive()
	{
		return "Check that AddToBootstrapClassLoaderSearch during Live correctly adds classes and resources. " +
		       "Added as a unit test for J9 VM design ID 450";		
	}

	private native boolean addJar();

	/**
	 * Check that the jar containing TestClass can be added to the boot path during the live phase.
	 * 
	 * @return true on pass
	 */
	public boolean testAddToBootpathDuringLive()
	{
		if (!addJar()) {
			return false;
		}
		/*
		 * Only test class loading.
		 * Resource loading doesn't work for the jar files added to the bootstrap classloader path. 
		 * RI Java 9/8 can't load resource from those jar files either after they are added to system classloader.
		 */
		return Util.testClassLoading("TestClass", null);
	}

}