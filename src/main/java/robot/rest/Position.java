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

public class Position {
    private int x;
    private int y;
    private String angle;
    
    public Position(final int x, final int y, final String angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    
    public Position() {
        
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final String getAngle() {
        return angle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((angle == null) ? 0 : angle.hashCode());
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (angle == null) {
            if (other.angle != null)
                return false;
        } else if (!angle.equals(other.angle))
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
    
    
}
