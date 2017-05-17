
import java.util.List;
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

post("/groups/:id/students/check-in", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Group group = Group.find(Integer.parseInt(request.params(":id")));
  Individual individual = Individual.find(Integer.parseInt(request.queryParams("studentId")));
  Sign sign = new Sign(individual.getId());
  model.put("individual", individual);
  // model.put("template", "templates/student-list.vtl");
  response.redirect("/student-groups");
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
