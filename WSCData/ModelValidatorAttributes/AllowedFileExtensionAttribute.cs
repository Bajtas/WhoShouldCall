using System;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WSCCore.Services
{
    [AttributeUsage(AttributeTargets.Property, AllowMultiple = false)]
    public class AllowedFileExtensionsAttribute : ValidationAttribute
    {
        public string[] AllowedFileExtensions { get; set; }
        public AllowedFileExtensionsAttribute(params string[] allowedFileExtensions)
        {
            AllowedFileExtensions = allowedFileExtensions;
        }
        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            var file = value as HttpPostedFileBase;
            if (file != null)
            {
                if (!AllowedFileExtensions.Any(item => file.FileName.EndsWith(item, StringComparison.OrdinalIgnoreCase)))
                {
                    return new ValidationResult(string.Format("{1} Allowed file formats : {0} : {2}", string.Join(", ", AllowedFileExtensions), validationContext.DisplayName, this.ErrorMessage));
                }
            }
            return null;
        }
    }
}