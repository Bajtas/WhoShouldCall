using System.Collections.Generic;
using System.IO;
using System.Web;
using WSCData.Models.Entities;

namespace WSCCore.Services
{
    public class ImagesService
    {
        private static readonly List<string> ALLOWED_EXTENSIONS = new List<string>
        {
            ".PNG", ".JPG", ".JPEG"
        };

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

        public static bool FilesAreValid(List<HttpPostedFileBase> menuImages)
        {
            bool ret = true;
            foreach(var image in menuImages)
            {
                string fileExtension = Path.GetExtension(image.FileName);
                if (!ALLOWED_EXTENSIONS.Contains(fileExtension.ToUpper()))
                {
                    ret = false;
                    break;
                }
            }

            return ret;
        }
    }
}