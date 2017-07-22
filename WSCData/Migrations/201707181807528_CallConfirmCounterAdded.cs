namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CallConfirmCounterAdded : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "UsersNeededToConfirmCall", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "UsersNeededToConfirmCall");
        }
    }
}
