using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Web;
using WSCCore.Services;
using WSCData.Models.Entities;

namespace WSCData.Models.ViewModels
{
    public class FoodProviderCreateViewModel
    {
        public FoodProvider FoodProvider { get; set; }
        [Display(Name = "Menu images")]
        [AllowedFileExtensions("JPG", "PNG")]
        public List<HttpPostedFileBase> MenuImages { get; set; }
    }
}
