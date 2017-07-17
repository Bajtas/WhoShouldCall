using System;
using System.Collections.Generic;
using System.Linq;

namespace WSCTools.WSCExceptions
{
    [Serializable]
    public class UnableToAddUserException : Exception
    {
        public UnableToAddUserException()
        { }

        public UnableToAddUserException(string message)
        : base(message)
        { }

        public UnableToAddUserException(IEnumerable<string> errors)
        : base(String.Format("Unable to add user to DB - {0}", string.Join("\n", errors.ToArray())))
        { }

        public UnableToAddUserException(string message, Exception innerException)
        : base(message, innerException)
        { }
    }
}