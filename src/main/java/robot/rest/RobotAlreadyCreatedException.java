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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.SEE_OTHER, reason="Robot already been created")
public class RobotAlreadyCreatedException extends RuntimeException {
private static final long serialVersionUID = -7601055175674926671L;
    
    public RobotAlreadyCreatedException() {
        super();
    }
    public RobotAlreadyCreatedException(final String message) {
        super(message);
    }
}
