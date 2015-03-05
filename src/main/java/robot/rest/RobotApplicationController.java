package robot.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import robot.Robot;
import robot.RobotApplication;
import robot.RobotApplicationImpl;

@Controller
public class RobotApplicationController {

    enum Command {
        left, right, move
    };

//    @Autowired
    private RobotApplication robotApplication = new RobotApplicationImpl();

    @RequestMapping(value = "/robot/{name}", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.CREATED)
    public void create(@PathVariable("name") String name) {
        if (robotApplication.getRobot(name) != null) {
            throw new RobotAlreadyCreatedException();
        }

        robotApplication.createRobot(name);
    }

    @RequestMapping(value = "/robot/list", method = RequestMethod.GET)
    public @ResponseBody String[] list() {
        return robotApplication.getRobotNames();
    }

    @RequestMapping(value = "/robot/position/{name}", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.CREATED)
    public @ResponseBody Position place(@PathVariable("name") String name, @RequestBody Position position) {
        final Robot robot = robotApplication.getRobot(name);
        robotApplication.place(robot, position.getX(), position.getY(), position.getAngle());

        return position;
    }

    @RequestMapping(value = "/robot/change/{name}/{command}", method = RequestMethod.PUT)
    public @ResponseBody Position change(@PathVariable("name") String name, @PathVariable("command") String command) {
        final Robot robot = robotApplication.getRobot(name);
        final Command robotCommand = Command.valueOf(command);
        switch (robotCommand) {
        case left:
            robotApplication.left(robot);
            break;
        case right:
            robotApplication.right(robot);
            break;
        case move:
            robotApplication.move(robot);
            break;
        }
        final Position newPosition = new Position(robot.getPosition().getX(), robot.getPosition().getY(), robot
                .getDirection().toString());

        return newPosition;
    }
    
    @RequestMapping(value = "/robot/report/{name}", method = RequestMethod.GET)
    public @ResponseBody Position report(@PathVariable("name") String name) {
        final Robot robot = robotApplication.getRobot(name);       
        final robot.Position robotPosition = robot.getPosition();
        if (robotPosition == null || robot.getDirection() == null) {
            throw new RobotNotPlacedException();
        }
        final Position newPosition = new Position(robotPosition.getX(), robotPosition.getY(), robot
                .getDirection().toString());

        return newPosition;
    }
}
