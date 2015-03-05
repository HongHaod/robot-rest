/*
 * =====================================================================
 * Copyright (c) NAB 2015
 * =====================================================================
 * (C) 2015 National Australia Bank Ltd [All rights reserved]. This
 * product and related documentation are protected by copyright
 * restricting its use, copying, distribution, and decompilation. No
 * part of this product or related documentation may be reproduced in
 * any form by any means without prior written authorization of
 * National Australia Bank Ltd. No part of this product can be sold,
 * leased, hired out, licensed or circulated in any way without the
 * written authorization of National Australia Bank Ltd. Unless
 * otherwise arranged, third parties may not have access to this
 * product or related documents.
 * =====================================================================
 */
package robot.rest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RobotApplicationControllerTest {

	private static final String BASE_URI = "http://localhost:8080/";

	private final RestTemplate restTemplate = new RestTemplate();

	@Test
    public void testAllRequests() {
    	// create
        final String robotName = "ROBOT_" + System.currentTimeMillis();
        URI location = restTemplate.postForLocation(BASE_URI + "robot/{name}", null, robotName);
        
        // list
        String[] names = restTemplate.getForObject(BASE_URI + "robot/list", String[].class);
        assertArrayEquals( new String[]{robotName}, names);
        
        // place
        Position expectedPosition = new Position(1, 1, "WEST");
        Position returnedPosition = restTemplate.postForObject(BASE_URI + "robot/position/{name}", expectedPosition, Position.class, robotName);
        assertEquals(expectedPosition, returnedPosition);
        
        // move
        expectedPosition = new Position(0, 1, "WEST");
        restTemplate.put(BASE_URI + "robot/change/{name}/{command}", null, robotName, RobotApplicationController.Command.move);
        returnedPosition = restTemplate.postForObject(BASE_URI + "robot/position/{name}", expectedPosition, Position.class, robotName);
        assertEquals(expectedPosition, returnedPosition);
        
        // left
        expectedPosition = new Position(0, 1, "SOUTH");
        restTemplate.put(BASE_URI + "robot/change/{name}/{command}", null, robotName, RobotApplicationController.Command.left);
        returnedPosition = restTemplate.postForObject(BASE_URI + "robot/position/{name}", expectedPosition, Position.class, robotName);
        assertEquals(expectedPosition, returnedPosition);
        
    }
}
