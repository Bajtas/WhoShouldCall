namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CallConfirmAddedToUser : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "IsCallConfirmed", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "IsCallConfirmed");
        }
    }
}
