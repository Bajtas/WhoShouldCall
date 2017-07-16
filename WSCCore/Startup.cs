using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(WSCCore.Startup))]
namespace WSCCore
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
