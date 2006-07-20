/*
 * Copyright (C) MX4J.
 * All rights reserved.
 *
 * This software is distributed under the terms of the MX4J License version 1.0.
 * See the terms of the MX4J License in the documentation provided with this software.
 */
/*
 *   Copyright 2005 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.apache.felix.mosgi.jmx.rmiconnector.mx4j.remote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import javax.management.MBeanServerConnection;

/**
 * @author <a href="mailto:biorn_steedom@users.sourceforge.net">Simone Bordet</a>
 * @version $Revision: 1.1.1.1 $
 */
public class ClientProxy implements InvocationHandler
{
   private final MBeanServerConnection target;

   protected ClientProxy(MBeanServerConnection target)
   {
      this.target = target;
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
   {
      try
      {
         return method.invoke(target, args);
      }
      catch (InvocationTargetException x)
      {
         throw x.getTargetException();
      }
   }
}
