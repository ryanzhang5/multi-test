/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.hduo;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Illustrates use of Hibernate native APIs.
 * 
 * @author Steve Ebersole
 */
public class NativeApiIllustrationTest extends TestCase {
	private ApplicationContext ctx;
	private TeamManager teamManager;

	@Override
	protected void setUp() throws Exception {

		ctx = new ClassPathXmlApplicationContext("context.xml");
		teamManager = (TeamManager) ctx.getBean("teamManager");

	}

	@Override
	protected void tearDown() throws Exception {

	}

	public void testBasicUsage() {

		teamManager.addTeam();

		//teamManager.insertTeam();

		//teamManager.selectTeams();
	}
}
