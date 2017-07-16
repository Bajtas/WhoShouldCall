using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Web;
using WSCData.Models.Entities;

namespace WSCData.Models.ViewModels
{
    public class FoodProviderCreateViewModel
    {
        public FoodProvider FoodProvider { get; set; }
        [Display(Name = "Menu images")]
        public List<HttpPostedFileBase> MenuImages { get; set; }
    }
}
