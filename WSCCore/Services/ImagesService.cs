using System.Collections.Generic;
using System.IO;
using System.Web;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public class ImagesService
    {
        public static ImageGallery SaveImages(List<HttpPostedFileBase> files)
        {
            ImageGallery gallery = new ImageGallery();
            foreach (var file in files)
            {
                Image image = new Image();
                using (Stream inputStream = file.InputStream)
                {
                    MemoryStream memoryStream = inputStream as MemoryStream;
                    if (memoryStream == null)
                    {
                        memoryStream = new MemoryStream();
                        inputStream.CopyTo(memoryStream);
                    }
                    image.Data = memoryStream.ToArray();
                }

                gallery.Images.Add(image);
            }

            return gallery;
        }
    }
}