using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web.Mvc;
using WSCCore.Services;
using WSCData.Models;
using WSCData.Models.Entities;
using WSCData.Models.ViewModels;

namespace WSCCore.Controllers
{
    public class FoodProvidersController : Controller
    {
        private WSCDbContext db = new WSCDbContext();

        // GET: FoodProviders
        public ActionResult Index()
        {
            return View(db.FoodProviders.ToList());
        }

        // GET: FoodProviders/Details/5
        public ActionResult Menu(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            FoodProvider foodProvider = db.FoodProviders.Find(id);
            if (foodProvider == null)
            {
                return HttpNotFound();
            }
            return View(new FoodProviderCreateViewModel { FoodProvider = foodProvider });
        }

        // GET: FoodProviders/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: FoodProviders/Create
        // Aby zapewnić ochronę przed atakami polegającymi na przesyłaniu dodatkowych danych, włącz określone właściwości, z którymi chcesz utworzyć powiązania.
        // Aby uzyskać więcej szczegółów, zobacz https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "FoodProvider, MenuImages")] FoodProviderCreateViewModel foodProviderVM)
        {
            if (ModelState.IsValid)
            {
                var foodProvider = foodProviderVM.FoodProvider;
                foodProvider.ImageGallery = ImagesService.SaveImages(foodProviderVM.MenuImages);
                db.FoodProviders.Add(foodProviderVM.FoodProvider);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(foodProviderVM);
        }

        // GET: FoodProviders/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            FoodProvider foodProvider = db.FoodProviders.Find(id);
            if (foodProvider == null)
            {
                return HttpNotFound();
            }
            return View(foodProvider);
        }

        // POST: FoodProviders/Edit/5
        // Aby zapewnić ochronę przed atakami polegającymi na przesyłaniu dodatkowych danych, włącz określone właściwości, z którymi chcesz utworzyć powiązania.
        // Aby uzyskać więcej szczegółów, zobacz https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name,FoodProviderRating")] FoodProvider foodProvider)
        {
            if (ModelState.IsValid)
            {
                db.Entry(foodProvider).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(foodProvider);
        }

        // GET: FoodProviders/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            FoodProvider foodProvider = db.FoodProviders.Find(id);
            if (foodProvider == null)
            {
                return HttpNotFound();
            }
            return View(foodProvider);
        }

        // POST: FoodProviders/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            FoodProvider foodProvider = db.FoodProviders.Find(id);
            db.FoodProviders.Remove(foodProvider);
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
