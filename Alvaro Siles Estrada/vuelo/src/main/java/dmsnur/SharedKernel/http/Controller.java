package dmsnur.SharedKernel.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.jboss.com.sun.net.httpserver.HttpExchange;

import dmsnur.SharedKernel.extensions.DependencyInjection;
import dmsnur.SharedKernel.http.Exception.HttpCodeException;
import dmsnur.SharedKernel.http.Exception.HttpException;
import dmsnur.SharedKernel.http.annotation.RequestMapping;
import dmsnur.SharedKernel.http.annotation.RestController;
import dmsnur.SharedKernel.mediator.IMediator;
import dmsnur.SharedKernel.mediator.Mediator;

public class Controller {
    private String route;
    private Class controller;
    private ArrayList<Action> actions;

    public Controller(Class controller) throws Exception {
        Annotation annotation = controller.getAnnotation(RestController.class);
        if (!(annotation instanceof RestController)) {
            throw new RuntimeException("El controlador no tiene la anotacion RestController");
        }
        annotation = controller.getAnnotation(RequestMapping.class);
        this.route = controller.getName();
        if ((annotation instanceof RequestMapping)) {
            this.route = ((RequestMapping) annotation).value();
        }
        this.controller = controller;
        actions = new ArrayList<Action>();
        this.initMethods();
    }

    private void initMethods() {
        Method[] methods = controller.getMethods();
        System.out.println("--------------------------------------");
        System.out.println("[CONTROLLER] " + this.route);
        for (Method method : methods) {
            Action action = null;
            try {
                action = new Action(method);
            } catch (HttpCodeException e) {
            }
            if (action == null) {
                continue;
            }
            actions.add(action);
            System.out.println("" + action.getType() + ":\t" + this.route + action.getRoute());

        }
        System.out.println("--------------------------------------");
    }

    public String getRoute() {
        return route;
    }

    public void onMessage(HttpExchange t, String data, Response response)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, HttpException {
        String path = t.getRequestURI().getPath();
        path = path.split("\\?")[0];
        path = path.substring(path.indexOf(route) + route.length());
        Method method = null;
        String requestMethod = t.getRequestMethod();
        if (path.length() == 0) {
            path = "/";
        }
        var exito = false;
        for (Action action : actions) {
            if (action.equal(requestMethod, path)) {
                Constructor<?> cos = this.controller.getConstructor(new Class[] { Mediator.class });
                IMediator m = new IMediator();
                action.onMessage(t, response, path, data, cos.newInstance(m));
                exito = true;
                break;
            }
        }
        if (!exito) {
            response.setCode(HttpStatus.BAD_GATEWAY);
            response.setBody("Method not found");
            return;
        }

    }
}