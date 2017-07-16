namespace WSCData.Models
{
    using Microsoft.AspNet.Identity.EntityFramework;
    using System.Data.Entity;
    using WSCData.Models.Entities;

    public class WSCDbContext : IdentityDbContext<User>
    {
        public WSCDbContext()
            : base("DefaultConnection", throwIfV1Schema: false)
        {
        }

        public static WSCDbContext Create()
        {
            return new WSCDbContext();
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<User>().ToTable("Users");
            modelBuilder.Entity<IdentityRole>().ToTable("Roles");
            modelBuilder.Entity<IdentityUserClaim>().ToTable("UserClaims");
            modelBuilder.Entity<IdentityUserLogin>().ToTable("UserLogins");
            modelBuilder.Entity<IdentityUserRole>().ToTable("UserRoles");
        }

        public virtual DbSet<Queue> Queues { get; set; }
        public virtual DbSet<Food> Foods { get; set; }
        public virtual DbSet<FoodProvider> FoodProviders { get; set; }
        public virtual DbSet<Order> Orders { get; set; }
    }
}