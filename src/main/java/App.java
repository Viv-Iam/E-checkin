import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

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
//ensures group names enterd are displayed on admin
    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("groups", Group.all());
      model.put("template", "templates/admin.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//adds group names to admin page
post("/groups", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("name");
  Group newGroup = new Group(name);
  newGroup.save();
  // model.put("template", "templates/group-success.vtl");
  response.redirect("/admin");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//gets group id
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
  model.put("template", "templates/student-list.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//lists all student names on the checkin template
post("/students/check-in", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Individual newIndividual = Individual.find(Integer.parseInt(request.queryParams("studentId")));
  Sign sign = new Sign(newIndividual.getId());
  model.put("newIndividual", newIndividual);
  response.redirect("/student-groups");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//retrieve sign-in with time and student name
get("/admin/class/register", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("signs", Sign.all());
  model.put("template", "templates/sign-in-list.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//saves new individual name
get("groups/:id/individuals/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params(":id")));
  model.put("group", group);
  model.put("template", "templates/group-individuals-form.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//saves names of all individuals with ids
post("/individuals", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();

  Group group = Group.find(Integer.parseInt(request.queryParams("groupId")));

  String name = request.queryParams("name");
  Individual newIndividual = new Individual(name, group.getId());
  newIndividual.save();
  model.put("group", group);
  response.redirect("/admin");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//retrieves data of individual after either being updated or deleted
get("/groups/:group_id/individuals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Group group = Group.find(Integer.parseInt(request.params(":group_id")));
      Individual individual = Individual.find(Integer.parseInt(request.params(":id")));
      model.put("group", group);
      model.put("individual", individual);
      model.put("template", "templates/individual.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//updates individual name
    post("/groups/:group_id/individuals/:id", (request, response) -> {
Map<String, Object> model = new HashMap<String, Object>();
Individual individual = Individual.find(Integer.parseInt(request.params("id")));
String name = request.queryParams("name");
Group group = Group.find(individual.getGroupId());
individual.update(name);
String url = String.format("/groups/%d/individuals/%d", group.getId(), individual.getId());
response.redirect(url);
return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//delete individual
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
