using Microsoft.AspNet.Identity.Owin;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WSCData.Models;
using WSCData.Models.Entities;
using WSCSecurity.Managers;

namespace WSCCore.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            using (var ctx = new WSCDbContext())
            {
                FoodProvider fp = new FoodProvider();
                fp.Name = "pierogarnia ta lepsza2";
                ctx.FoodProviders.Add(fp);

                var user = ctx.Users.Where(u => u.Email == "admin@gmail.com").FirstOrDefault();
                Order order = new Order();
                order.FoodProvider = fp;
                order.Date = System.DateTime.Now;
                user.Orders.Add(order);

                Order order2 = new Order();
                order2.FoodProvider = fp;
                order2.Date = System.DateTime.Now;
                user.Orders.Add(order2);

                Order order3 = new Order()
                {
                    FoodProvider = fp,
                    Date = System.DateTime.Now
                };
                user.Orders.Add(order3);

                Order order4 = new Order()
                {
                    FoodProvider = fp,
                    Date = System.DateTime.Now
                };
                user.Orders.Add(order4);

                ctx.SaveChanges();
            }
            var result = HttpContext.GetOwinContext().Get<ApplicationSignInManager>().PasswordSignInAsync("admin@gmail.com", "Test123!", false, shouldLockout: false);
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}