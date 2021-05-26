/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.vdot.fragment.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.vdot.api.INimeconfirmService;
import org.openmrs.module.vdot.api.NimeconfirmVideoObs;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for vdot observation page
 */
public class VdotPillCalendarFragmentController {
	
	INimeconfirmService service = Context.getService(INimeconfirmService.class);
	
	public void controller(@RequestParam("patientId") Patient patient, PageModel model, UiUtils ui) {
		/*var patientO = "{\n" +
				"\t\"dateofEnrollment\": \"2021-04-07\",\n" +
				"\t\"dates\": [\"2021-04-07 18:55:12\", \"2021-04-08 07:33:41\", \"2021-04-08 19:10:53\", \"2021-04-09 07:18:34\", \"2021-04-09 19:05:52\", \"2021-04-10 07:10:29\", \"2021-04-10 19:11:12\", \"2021-04-11 07:07:34\", \"2021-04-11 19:12:46\", \"2021-04-12 06:59:52\", \"2021-04-12 19:46:01\", \"2021-04-13 07:07:40\", \"2021-04-13 18:52:05\", \"2021-04-14 07:00:23\", \"2021-04-14 19:00:22\", \"2021-04-15 07:02:22\", \"2021-04-15 19:05:18\", \"2021-04-16 07:01:01\", \"2021-04-16 18:57:34\", \"2021-04-17 19:00:31\", \"2021-04-18 07:02:11\", \"2021-04-18 19:06:12\", \"2021-04-19 07:13:58\", \"2021-04-19 18:52:03\", \"2021-04-20 07:04:51\", \"2021-04-20 19:01:15\", \"2021-04-21 07:00:55\", \"2021-04-21 18:52:30\", \"2021-04-22 07:07:28\", \"2021-04-22 18:58:56\", \"2021-04-23 06:55:45\", \"2021-04-23 18:37:52\", \"2021-04-24 06:51:42\", \"2021-04-24 18:59:00\", \"2021-04-25 06:52:00\", \"2021-04-25 18:56:13\", \"2021-04-26 06:59:41\", \"2021-04-27 06:45:30\", \"2021-04-27 18:54:07\", \"2021-04-28 06:46:26\", \"2021-04-28 18:51:01\", \"2021-04-29 06:41:41\", \"2021-04-29 18:52:03\", \"2021-04-30 06:46:19\", \"2021-04-30 18:56:10\", \"2021-05-01 06:49:38\", \"2021-05-01 18:51:33\", \"2021-05-02 07:03:05\", \"2021-05-02 18:50:54\", \"2021-05-03 06:51:49\", \"2021-05-03 19:12:13\", \"2021-05-04 06:45:22\", \"2021-05-04 19:04:51\", \"2021-05-05 06:45:25\", \"2021-05-05 18:52:15\", \"2021-05-06 06:47:33\", \"2021-05-06 18:51:50\", \"2021-05-07 06:47:03\", \"2021-05-07 18:52:06\", \"2021-05-08 06:59:17\", \"2021-05-08 18:55:48\", \"2021-05-09 06:39:46\", \"2021-05-09 19:00:28\", \"2021-05-10 06:49:49\", \"2021-05-10 18:52:14\", \"2021-05-11 06:51:25\", \"2021-05-12 06:51:36\"]\n" +
				"}";*/
		
		List<NimeconfirmVideoObs> allVideoObs = service.getNimeconfirmVideoObsByPatient(patient);
		String b = "";
		List<String> allVideoTimestamps = new ArrayList<String>();
		if (allVideoObs != null && !allVideoObs.isEmpty()) {
			for (NimeconfirmVideoObs vObs : allVideoObs) {
				allVideoTimestamps.add(vObs.getTimeStamp());
			}
		}
		b = StringUtils.join(allVideoTimestamps, ",");
		
		Map<String, Object> jsonConfig = new LinkedHashMap<String, Object>();
		jsonConfig.put("dateofEnrollment", "2021-04-07");
		if (StringUtils.isNotBlank(b)) {
			jsonConfig.put("dates", Arrays.asList(b.split(",")));
		} else {
			jsonConfig.put("dates", new ArrayList<String>());
		}
		//jsonConfig.put("dates", Arrays.asList(b.split(",")));
		
		model.put("patientData", ui.toJson(jsonConfig));
		
	}
}
