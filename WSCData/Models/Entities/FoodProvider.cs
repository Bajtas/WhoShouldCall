using System.ComponentModel.DataAnnotations;

namespace WSCData.Models.Entities
{
    public class FoodProvider
    {
        public int Id { get; set; }
        public string Name { get; set; }
        [Display(Name = "Rating")]
        public decimal Rating { get; set; }
        public ImageGallery ImageGallery { get; set; }
    }
}