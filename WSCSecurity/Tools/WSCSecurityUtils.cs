using WSCData.Models;

namespace WSCSecurity.Tools
{
    static class WSCSecurityUtils
    {
        public static WSCDbContext getContext()
        {
            return new WSCDbContext();
        }
    }
}