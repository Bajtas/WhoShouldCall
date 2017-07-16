using System.Collections.Generic;

namespace WSCData.Models.Entities
{
    public class ImageGallery
    {
        public List<Image> Images { get; set; }

        public ImageGallery()
        {
            Image = new List<Image>();
        }
    }
}