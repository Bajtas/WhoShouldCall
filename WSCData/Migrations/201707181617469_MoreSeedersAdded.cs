namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class MoreSeedersAdded : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "IsCaller", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "IsCaller");
        }
    }
}
