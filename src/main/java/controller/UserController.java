package controller;

import model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView loadRegisterForm() {
        logger.trace("Go into loadFormRegister()");
        ModelAndView registerForm = null;
        User user;
        try {
            user = new User();
            registerForm = new ModelAndView("index");
            registerForm.addObject("user", user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadFormRegister()");
        return registerForm;
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public ModelAndView result(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        logger.trace("Go into result()");
        logger.info(user.toString());
        ModelAndView result;
        if (bindingResult.hasFieldErrors()) {
            result = new ModelAndView("index");
            return result;
        }
        result = new ModelAndView("result");
        logger.trace("Exit result()");
        return result;
    }
}
