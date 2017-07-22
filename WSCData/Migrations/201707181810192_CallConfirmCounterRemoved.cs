namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CallConfirmCounterRemoved : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.Users", "UsersNeededToConfirmCall");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Users", "UsersNeededToConfirmCall", c => c.Int(nullable: false));
        }
    }
}
