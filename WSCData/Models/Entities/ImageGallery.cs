using System.Collections.Generic;

namespace WSCData.Models.Entities
{
    public class ImageGallery
    {
        public int Id { get; set; }
        public List<Image> Images { get; set; }

        public ImageGallery()
        {
            Images = new List<Image>();
        }
    }
}