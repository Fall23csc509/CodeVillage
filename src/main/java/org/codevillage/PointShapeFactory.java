package org.codevillage;

public class PointShapeFactory
{
    int BIG_HOUSE_LINES_OF_CODE_THRESHOLD = 150;
    public static Shape createShapeForJavaEntity(JavaEntity entity)
    {
        /*
        if(entity instanceof JavaAbstractClass)
            return new Rock();
        else if(entity instanceof JavaInterface)
            return new Tree();
        else if(entity instanceof JavaBaseClass)
        {
            if(entity.getLinesOfCode() > BIG_HOUSE_THRESHOLD)
                return new BigHouse();
            else
                return new SmallHouse();
        }
        */
        return null;
    }
}
