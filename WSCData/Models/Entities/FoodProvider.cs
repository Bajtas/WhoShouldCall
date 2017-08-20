using System.Collections.Generic;
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
        public virtual List<Food> Menu { get; set; }

        public override bool Equals(object obj)
        {
            if (obj == null || GetType() != obj.GetType())
            {
                return false;
            }
            FoodProvider fp = obj as FoodProvider;

            return Id.Equals(fp?.Id);    
        }
    }
}