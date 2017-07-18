using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web.Mvc;
using WSCCore.Services;
using WSCCore.Services.Queues;
using WSCData.Models;
using WSCData.Models.Entities;
using WSCData.Models.ViewModels;

namespace WSCCore.Controllers
{
    public class QueuesController : Controller
    {
        private WSCDbContext db = new WSCDbContext();

        // GET: Queues
        public ActionResult Index()
        {
            return View(db.Queues.ToList());
        }

        // GET: Queues/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(id);
            if (queue == null)
            {
                return HttpNotFound();
            }

            QueueDetailsViewModel vm = new QueueDetailsViewModel
            {
                Queue = queue,
                FoodProviders = db.FoodProviders.ToList()
            };
            return View("Details", vm);
        }

        public ActionResult Join(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(id);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueJoinAndLeaveService(db);
            queueService.UserRemoveFromQueue(User, queue);
            queueService.UserJoinToQueue(User, queue);
            db.SaveChanges();
            return Details(id);
        }

        public ActionResult Leave(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(id);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueJoinAndLeaveService(db);
            queueService.UserRemoveFromQueue(User);
            db.SaveChanges();
            return View("Index", db.Queues.ToList());
        }

        public ActionResult ImCaller(int? queueId, string userName)
        {
            if (string.IsNullOrEmpty(userName) || queueId == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(queueId);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueCallerService = new QueueCallerService(db);
            var queueUserStateManagerService = new QueueUserStateManagerService(db);
            if (!queueUserStateManagerService.IsUserCaller(userName))
            {
                queueCallerService.RemoveCurrentCaller(queueId);
                queueCallerService.SetUserAsCallerAndUpdateLastCallDate(userName);
                db.SaveChanges();
            }
            else
            {
                ModelState.AddModelError(string.Empty, "You are already a caller!");
            }
            return View("Details", queue);
        }

        [Authorize(Roles = "Admin, Moderator")]
        public ActionResult ConfirmCall(int? queueId, string userName)
        {
            if (string.IsNullOrEmpty(userName) || queueId == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(queueId);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueUserStateManagerService(db);
            if (queueService.IsUserCaller(userName))
            {
                queueService.ConfirmCall(userName);
                queueService.FinalizeCaller(userName);
                db.SaveChanges();
            }
            else
            {
                ModelState.AddModelError(string.Empty, "Call confirmed!");
            }
            return View("Details", queue);
        }

        [Authorize(Roles = "Admin, Moderator")]
        public ActionResult SetAsModerator(int? queueId, string userName)
        {
            if (string.IsNullOrEmpty(userName) || queueId == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(queueId);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueUserStateManagerService(db);
            queueService.SetUserAsModerator(queueId, userName);
            db.SaveChanges();
            return View("Details", queue);
        }

        [Authorize(Roles = "Admin, Moderator")]
        public ActionResult RemoveModerator(int? queueId, string userName)
        {
            if (string.IsNullOrEmpty(userName) || queueId == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(queueId);
            if (queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueUserStateManagerService(db);
            queueService.RemoveModerator(queueId, userName);
            db.SaveChanges();
            return View("Details", queue);
        }

        [Authorize(Roles = "Admin, Moderator")]
        public ActionResult RemoveUser(int? queueId, string userName)
        {
            if (string.IsNullOrEmpty(userName))
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User user = db.Users.Where(u => u.UserName == userName).FirstOrDefault();
            Queue queue = db.Queues.Find(queueId);
            if (user == null || queue == null)
            {
                return HttpNotFound();
            }
            var queueService = new QueueUserStateManagerService(db);
            queueService.RemoveUser(queue.Id, userName);
            db.SaveChanges();
            return Details(queueId);
        }

        [Authorize(Roles = "Admin, Moderator")]
        public ActionResult EditUser(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User user = db.Users.Find(id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View("EditUser", user);
        }

        // GET: Queues/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Queues/Create
        // Aby zapewnić ochronę przed atakami polegającymi na przesyłaniu dodatkowych danych, włącz określone właściwości, z którymi chcesz utworzyć powiązania.
        // Aby uzyskać więcej szczegółów, zobacz https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name")] Queue queue)
        {
            if (ModelState.IsValid)
            {
                db.Queues.Add(queue);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(queue);
        }

        // GET: Queues/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(id);
            if (queue == null)
            {
                return HttpNotFound();
            }
            return View(queue);
        }

        // POST: Queues/Edit/5
        // Aby zapewnić ochronę przed atakami polegającymi na przesyłaniu dodatkowych danych, włącz określone właściwości, z którymi chcesz utworzyć powiązania.
        // Aby uzyskać więcej szczegółów, zobacz https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name")] Queue queue)
        {
            if (ModelState.IsValid)
            {
                db.Entry(queue).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(queue);
        }

        // GET: Queues/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Queue queue = db.Queues.Find(id);
            if (queue == null)
            {
                return HttpNotFound();
            }
            return View(queue);
        }

        // POST: Queues/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Queue queue = db.Queues.Find(id);
            db.Queues.Remove(queue);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
