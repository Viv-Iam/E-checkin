
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import javax.swing.JOptionPane;

public class App {
  public static void main(String[] args) {

    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") != null) {
        port = Integer.parseInt(process.environment().get("PORT"));
    } else {
        port = 4567;
    }

   setPort(port);


    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    //login
    post("/login", (request,response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String password = request.queryParams("password");
      if(name.equals(User.USER_NAME) && password.equals(User.USER_PASSWORD)) {
        User user = new User(name,password, User.USER_TYPE[1]);
        user.save();
        request.session().attribute("user", user);
        User logInUser=User.findLogin(password);

        String url;

          request.session().attribute("user",logInUser);
           url = "/admin";

        response.redirect(url);
      }else {
        String url;
          JOptionPane.showMessageDialog(null, "Incorrect Username or Password!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
             url = "/login";
             response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("groups", Group.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/classlist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/classlist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/classlist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Group group = Group.find(Integer.parseInt(request.params(":id")));
      model.put("template", "templates/classlist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("groups", Group.all());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //login
    get("/login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/signin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


//     get("/groups/new", (request, response) -> {
//   Map<String, Object> model = new HashMap<String, Object>();
//   model.put("template", "templates/group-form.vtl");
//   return new ModelAndView(model, layout);
// }, new VelocityTemplateEngine());

post("/groups", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("name");
  Group newGroup = new Group(name);
  newGroup.save();
  // model.put("template", "templates/group-success.vtl");
  response.redirect("/");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/groups/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params(":id")));
  model.put("group", group);
  model.put("template", "templates/group.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//all the groups for students to choose
get("/student-groups", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("groups", Group.all());
  model.put("template", "templates/student.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());
//students in a group
get("/groups/:id/students", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params(":id")));
  model.put("group", group);
  model.put("template", "templates/classlist.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/groups/students/show/", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.queryParams("groupId")));
  model.put("group", group);
  model.put("template", "templates/student-list.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

post("/students/check-in", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  // Group group = Group.find(Integer.parseInt(request.params(":id")));
  Individual individual = Individual.find(Integer.parseInt(request.queryParams("studentId")));
  Sign sign = new Sign(individual.getId());
  model.put("individual", individual);
  // model.put("template", "templates/student-list.vtl");
  response.redirect("/");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/admin/class/register", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.queryParams("groupId")));
  model.put("group", group);
  model.put("signs", Sign.all());
  model.put("template", "templates/class-register.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("groups/:id/individuals/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params(":id")));
  model.put("group", group);
  model.put("template", "templates/group-individuals-form.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

post("/individuals", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();

  Group group = Group.find(Integer.parseInt(request.queryParams("groupId")));

  String name = request.queryParams("name");
  Individual newIndividual = new Individual(name, group.getId());
  newIndividual.save();

  model.put("group", group);
  // model.put("template", "templates/group-individuals-success.vtl");
  response.redirect("/");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/groups/:group_id/individuals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Group group = Group.find(Integer.parseInt(request.params(":group_id")));
      Individual individual = Individual.find(Integer.parseInt(request.params(":id")));
      model.put("group", group);
      model.put("individual", individual);
      model.put("template", "templates/individual.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/admin/class/register", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  // Individual individual = new Individual(:studentId);
  model.put("signs", Sign.all());
  // model.put("individual", individual);
  model.put("template", "templates/sign-in-list.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//deleting groups
post("/groups/:group_id/delete", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params("group_id")));
  group.delete();
  model.put("group", group);
  model.put("template", "templates/groups.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());
//all groups
get("/groups", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();

  model.put("groups", Group.all());
  model.put("template", "templates/groups.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());


    post("/groups/:group_id/individuals/:id", (request, response) -> {
Map<String, Object> model = new HashMap<String, Object>();
Individual individual = Individual.find(Integer.parseInt(request.params("id")));
String name = request.queryParams("name");
Group group = Group.find(individual.getGroupId());
individual.update(name);
// String url = String.format("/groups/%d/individuals/%d", group.getId(), individual.getId());
String url = "/groups/students/show/?groupId=" + request.params("group_id");
response.redirect(url);
// model.put("template", "templates/student-list.vtl");
return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

post("/groups/:group_id/individuals/:id/delete", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Individual individual = Individual.find(Integer.parseInt(request.params("id")));
  Group group = Group.find(individual.getGroupId());
  individual.delete();
  model.put("group", group);
  model.put("template", "templates/group.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

  }
}
